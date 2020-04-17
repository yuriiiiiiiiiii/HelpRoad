package kr.ac.hansung.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hansung.model.Store;
import kr.ac.hansung.service.StoreService;

@Controller
public class StoreController {
	
	@Autowired
	private StoreService storeService;
	
	@RequestMapping("/map")
	public String showStore(Model model) {
		List<Store> store = storeService.getCurrent();
		
		model.addAttribute("store", store);
	return "map";
	}
	
	/*@RequestMapping("/createstore")
	public String createstore(Model model) {
		
		model.addAttribute("store", new Store());
		
		return "createstore";
	}*/
	


}
