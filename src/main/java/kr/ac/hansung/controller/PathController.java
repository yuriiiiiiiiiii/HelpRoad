 package kr.ac.hansung.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.hansung.model.Point;
import kr.ac.hansung.service.ConversionService;
import kr.ac.hansung.service.PathFindingService;
import kr.ac.hansung.service.StoreService;

@Controller
public class PathController {

	@Autowired
	private ConversionService conversionService;

	@Autowired
	private StoreService storeService;

	@Autowired
	private PathFindingService pathFindingService;

	// 위도값 경도값 받아서 길 안내 시작
	@RequestMapping(value = "/path", method = { RequestMethod.GET, RequestMethod.POST })
	public String conversion(Model model, HttpServletRequest request) { // 현재 위치(위도, 경도, 층), 도착 위치(매장 층, 이름) 전달

		// current : 현재 위치 행렬
		Point current = new Point();
		
		if(request.getParameter("latitude") != null && request.getParameter("longitude") != null) {	// 현재 위치를 얻었을 때
			current = conversionService.mappingMap(Double.parseDouble(request.getParameter("latitude")), Double.parseDouble(request.getParameter("longitude"))); // 현재 위도, 경도 -> 행렬 좌표
		}		
		else {	// elevator에서 길 찾기 재개할 때
			current.setPoint(Integer.parseInt(request.getParameter("startpx")), Integer.parseInt(request.getParameter("startpy")));
		}
		
		int startFloor;
		int endFloor;
		
		if(request.getParameter("startFloor") != null && request.getParameter("endFloor") != null) {	// 출발 층과 도착 층을 얻었을 때
			startFloor = Integer.parseInt(request.getParameter("startFloor"));
			endFloor = Integer.parseInt(request.getParameter("endFloor"));
		}
		else {	// elevator에서 길 찾기 재개할 때
			startFloor = Integer.parseInt(request.getParameter("startFloor"));
			endFloor = startFloor;
		}

		String storeName = request.getParameter("name");
		
		// destination : 도착 행렬 위치
		Point destination = storeService.getStorePoint(endFloor, storeName);
		
		if (startFloor != endFloor) {	// 현재 층과 도착 층이 다를 때
			Point elve1 = storeService.getStorePoint(startFloor, "엘리베이터1");
			Point elve2 = storeService.getStorePoint(startFloor, "엘리베이터2");

			// 가까운 엘베로 향하는 경로 만들기
			pathFindingService.getRoute(current, startFloor, elve1, elve2);
			//pathFindingService.draw();
			
			Point elvep = new Point();
			elvep.setPoint(pathFindingService.getElveX(), pathFindingService.getElveY());
			
			model.addAttribute("startpx", elvep.getX());	// 엘베 내린 후 x 좌표
			model.addAttribute("startpy", elvep.getY());	// 엘베 내린 후 y 좌표
			model.addAttribute("startFloor", endFloor);	// 엘베 내린 후 출발 층
			model.addAttribute("name", storeName);	// 도착 매장
			
		}
		else {	// 현재 층과 도착 층이 같을 때
			
			// 경로 만들기
			pathFindingService.getRoute(current, startFloor, destination);
			//pathFindingService.draw();
		}
		
		// 방향 안내
		String s = request.getParameter("heading");
	
		String heading = pathFindingService.navigation(Double.parseDouble(s));

		model.addAttribute("heading", heading);

		return "path";
	}

	// 1.5초마다 호출, 길 안내하는 중
	@RequestMapping(value = "/path.do", method = RequestMethod.POST)
	@ResponseBody
	public String guide(@RequestParam String heading) { // 현재 위치(위도, 경도, 방향) 전달

		heading = pathFindingService.navigation(Double.parseDouble(heading));	// 방향 안내
		
		return heading;
	}
	
	// 엘리베이터로 값 전달
	@RequestMapping(value = "/path/elevator", method = { RequestMethod.GET, RequestMethod.POST })
	public String elevater(Model model, HttpServletRequest request) {
		
		model.addAttribute("startpx", request.getParameter("startpx"));	// 엘베 내린 후 x 좌표
		model.addAttribute("startpy", request.getParameter("startpy"));	// 엘베 내린 후 y 좌표
		model.addAttribute("startFloor", request.getParameter("startFloor"));	// 엘베 내린 후 출발 층
		model.addAttribute("name", request.getParameter("name"));	// 엘베 내린 후 도착 매장
		
		return "elevator";
	}
	
}
