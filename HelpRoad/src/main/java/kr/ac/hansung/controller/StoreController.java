package kr.ac.hansung.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	//층과 이름으로 매장 찾기
	@RequestMapping(value="/{floor}/{name}", method=RequestMethod.GET)
	public String map(Model model, @PathVariable int floor, @PathVariable String name) {
		
		Store store = storeService.getStoreByfloorNname(floor, name);
		model.addAttribute("store", store);
		
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
