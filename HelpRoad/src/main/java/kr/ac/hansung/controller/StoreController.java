package kr.ac.hansung.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.hansung.model.Store;
import kr.ac.hansung.service.StoreService;

@Controller
@RequestMapping("/map")
public class StoreController {
	
	@Autowired
	private StoreService storeService;
	
	@RequestMapping
	public String map(Model model) {
	
		
		return "map";
	}
	
	//검색창
	@RequestMapping("/search")
	public String showSearchWindow() {
		return "search";
	}
	
	//검색결과
	@RequestMapping("/search/list")
	public String showStores(Model model, @RequestParam(value="name", required=false) String name) {
		
		List<Store> stores = storeService.getCurrent(name);
		model.addAttribute("stores", stores);
		
		return "list";
	}


}
