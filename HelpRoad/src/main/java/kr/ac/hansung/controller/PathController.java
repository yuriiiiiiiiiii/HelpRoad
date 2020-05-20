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
	@RequestMapping(value = "/path", method = {RequestMethod.GET, RequestMethod.POST})
	public String conversion(Model model, HttpServletRequest request) { // 현재 위치(위도, 경도, 층), 도착 위치(매장 층, 이름) 전달

		// current : 현재 위치 행렬
		// Point current =
		// conversionService.mappingMap(Double.parseDouble(request.getParameter("latitude")),
		// Double.parseDouble(request.getParameter("longitude"))); // 현재 위도, 경도 -> 행렬 좌표

		Point current = conversionService.mappingMap(37.560321, 126.980490); // 임의 좌표

		// destination : 도착 위치 행렬
		try {
			Point destination = storeService.getStorePoint(Integer.parseInt(request.getParameter("floor")),
					request.getParameter("name"));

			// 경로 만들기
			pathFindingService.getRoute(current, destination);
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:/path";
		}

		// 방향 안내
		String s = request.getParameter("heading");

		if (s == null || s.equals(""))
			s = "0";

		String heading = pathFindingService.navigation(Double.parseDouble(s));

		if (heading.equals("error"))
			System.out.println("에러다 꺄핳하ㅏ핳ㅎㅎ");

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

		//System.out.println(request.getParameter("latitude"));
		//System.out.println(request.getParameter("longitude"));
		System.out.println(heading);
		
		// Point current =
		// conversionService.mappingMap(Double.parseDouble(request.getParameter("latitude")),
		// Double.parseDouble(request.getParameter("longitude"))); // 현재 위도, 경도 -> 행렬 좌표
		// current : 현재 위치 행렬

		// 방향 안내
		/*String s = request.getParameter("heading");

		if (s == null || s.equals(""))
			s = "0";
		System.out.println(s);*/
		if(heading == null || heading.equals(""))
			heading = "0";
		System.out.println(heading);
		
		String direction = pathFindingService.navigation(Double.parseDouble(heading));

		// 경로 방향 에러
		if (direction.equals("error"))
			System.out.println("에러다 꺄핳하ㅏ핳ㅎㅎ");

		System.out.println(direction);

		//model.addAttribute("direction", direction);

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
