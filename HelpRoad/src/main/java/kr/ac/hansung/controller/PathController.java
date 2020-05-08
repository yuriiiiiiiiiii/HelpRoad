package kr.ac.hansung.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	// 위도값 경도값 받아 오기
    @RequestMapping(value="/path", method=RequestMethod.POST)
    public String conversion(Model model, HttpServletRequest request){  //	현재 위치(위도, 경도, 층), 도착 위치(매장 층, 이름) 전달

    	//Point current = conversionService.mappingMap(Double.parseDouble(request.getParameter("latitude")), Double.parseDouble(request.getParameter("longitude")));	//	현재 위도, 경도 -> 행렬 좌표
    	//	current : 현재 위치 행렬
    	
    	Point current = conversionService.mappingMap(37.560321, 126.980490);	//	임의 좌표
    	
    	Point destination = storeService.getStorePoint(Integer.parseInt(request.getParameter("floor")), request.getParameter("name"));
    	// destination : 도착 위치 행렬
    
    	pathFindingService.getRoute(current, destination);
    	
    	return "path";
    }
    /*
    @RequestMapping(value="latitude") //??
    public String getCurrent(Model model, @RequestParam(value="latitude") String latitude,
														@RequestParam(value="longitude") String longitude) throws Exception {
       
        List<Current> current = currentService.getCurrent(latitude, longitude);        
        model.addAttribute("latitude", latitude );
        model.addAttribute("longitude", longitude );
        
        return "current";
    }
      */  
    /*@RequestMapping(value="/list.jsp", method=RequestMethod.GET)

    public String getCurrent(Current current, Model model){
           
           String latitude = current.getLatitude();
           String longitude = current.getLongitude();
           model.addAttribute("latitude", latitude);
           model.addAttribute("longitude", longitude);

           return "current";
    }*/
}
