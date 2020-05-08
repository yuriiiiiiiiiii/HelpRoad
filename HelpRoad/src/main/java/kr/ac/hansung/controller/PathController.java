package kr.ac.hansung.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.hansung.model.Current;
import kr.ac.hansung.model.Point;
import kr.ac.hansung.service.ConversionService;
import kr.ac.hansung.service.CurrentService;
import kr.ac.hansung.service.LinkedListService;
import kr.ac.hansung.service.PathFindingService;



@Controller
public class PathController {
	
	@Autowired
	private ConversionService conversionService;
	/*
	 * @Autowired private LinkedListService linkedListService;
	 * 
	 * @Autowired private PathFindingService pathFindingService;
	 * 
	 * @Autowired private CurrentService currentService;
	 */
	
	// 위도값 경도값 받아 오기
    @RequestMapping(value="/path", method=RequestMethod.POST)

    public String conversionCurrent(Model model, @RequestParam(value="latitude") double latitude, @RequestParam(value="longitude") double longitude)
    {
           
    	Point current = new Point();
    	current.setPoint(latitude, longitude);
    	model.addAttribute("current", current);
    	conversionService.mappingMap(current);

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
