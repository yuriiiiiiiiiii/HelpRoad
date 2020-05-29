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
		//Point current = conversionService.mappingMap(Double.parseDouble(request.getParameter("latitude")),
		//Double.parseDouble(request.getParameter("longitude"))); // 현재 위도, 경도 -> 행렬 좌표

		Point current = conversionService.mappingMap(37.560321, 126.980490); // 임의 좌표

		System.out.println(Integer.parseInt(request.getParameter("startFloor")));
		System.out.println(request.getParameter("floor"));
		System.out.println(request.getParameter("name"));

		int startFloor = Integer.parseInt(request.getParameter("startFloor"));
		int endFloor = Integer.parseInt(request.getParameter("floor"));

		if (startFloor != endFloor) {
			Point destination1 = storeService.getStorePoint(startFloor, "엘리베이터1");
			Point destination2 = storeService.getStorePoint(startFloor, "엘리베이터2");

			/*System.out.println(current);
			System.out.println(startFloor);
			System.out.println(destination1);
			System.out.println(destination2);*/
			pathFindingService.getRoute(current, startFloor, destination1, destination2);
			pathFindingService.draw();
			
		} else {
			// destination : 도착 위치 행렬
			Point destination = storeService.getStorePoint(endFloor, request.getParameter("name"));

			System.out.println(current.getX()+","+current.getY());
			System.out.println(startFloor);
			System.out.println(destination.getX()+","+destination.getY());
			// 경로 만들기
			pathFindingService.getRoute(current, startFloor, destination);
			pathFindingService.draw();
		}
		/*
		 * try { Point destination =
		 * storeService.getStorePoint(Integer.parseInt(request.getParameter("floor")),
		 * request.getParameter("name"));
		 * 
		 * // 경로 만들기 pathFindingService.getRoute(current, destination); }catch(Exception
		 * e){ e.printStackTrace(); return "redirect:/path"; }
		 */

		// 방향 안내
		String s = request.getParameter("heading");

		if (s == null || s.equals(""))
			s = "0";

		String heading = pathFindingService.navigation(Double.parseDouble(s));

		// 경로 방향 안내 에러
		if (heading.equals("error"))
			return "redirect:/map";

		model.addAttribute("heading", heading);

		System.out.println(heading);
		System.out.println(request.getParameter("latitude"));
		System.out.println(request.getParameter("longitude"));

		return "path";
	}

	// 2초마다 호출, 길 안내하는 중
	@RequestMapping(value = "/path.do", method = RequestMethod.POST)
	@ResponseBody
	public String position(@RequestParam String heading) { // 현재 위치(위도, 경도, 방향) 전달

		// System.out.println(request.getParameter("latitude"));
		// System.out.println(request.getParameter("longitude"));
		System.out.println(heading);

		// Point current =
		// conversionService.mappingMap(Double.parseDouble(request.getParameter("latitude")),
		// Double.parseDouble(request.getParameter("longitude"))); // 현재 위도, 경도 -> 행렬 좌표
		// current : 현재 위치 행렬

		// 방향 안내
		/*
		 * String s = request.getParameter("heading");
		 * 
		 * if (s == null || s.equals("")) s = "0"; System.out.println(s);
		 */
		if (heading == null || heading.equals(""))
			heading = "0";
		System.out.println(heading);

		String direction = pathFindingService.navigation(Double.parseDouble(heading));

		System.out.println(direction);

		// model.addAttribute("direction", direction);

		return direction;
	}

	/*
	 * @RequestMapping(value="latitude") //?? public String getCurrent(Model
	 * model, @RequestParam(value="latitude") String latitude,
	 * 
	 * @RequestParam(value="longitude") String longitude) throws Exception {
	 * 
	 * List<Current> current = currentService.getCurrent(latitude, longitude);
	 * model.addAttribute("latitude", latitude ); model.addAttribute("longitude",
	 * longitude );
	 * 
	 * return "current"; }
	 */
	/*
	 * @RequestMapping(value="/list.jsp", method=RequestMethod.GET)
	 * 
	 * public String getCurrent(Current current, Model model){
	 * 
	 * String latitude = current.getLatitude(); String longitude =
	 * current.getLongitude(); model.addAttribute("latitude", latitude);
	 * model.addAttribute("longitude", longitude);
	 * 
	 * return "current"; }
	 */
}
