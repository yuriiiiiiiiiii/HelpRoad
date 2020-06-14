package kr.ac.hansung.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import kr.ac.hansung.model.Store;
import kr.ac.hansung.service.StoreService;

@Controller
public class StoreController {

   @Autowired
   private StoreService storeService;

   @RequestMapping ("/search")
   public String map(Model model) {
     
     model.addAttribute("store", new Store());
    
      return "search";
   }

   // 검색 매장 리스트
   @RequestMapping("/dosearch")
   public String showStores(Model model, @RequestParam(value = "name", required = false) String name, @Valid Store store, BindingResult result) {

     if(result.hasErrors()) {	// 입력 데이터에 에러가 있으면
        System.out.println("vaildation 에러");
        List<ObjectError> errors = result.getAllErrors();
        
        for(ObjectError error: errors) {
           System.out.println(error.getDefaultMessage());
        }
        return "search";
     }
      
      List<Store> stores = storeService.getCurrent(name);

      while(true) {
         boolean noElivator = true;

         for(int i = 0; i < stores.size(); i++) {
            if(stores.get(i).getName().equals("엘리베이터1")) {	// 검색 리스트에 엘리베이터가 안 보이도록
               stores.remove(i);
               noElivator = false;
               break;
            }
            if(stores.get(i).getName().equals("엘리베이터2")) {
               stores.remove(i);
               noElivator = false;
               break;
            }
         }
         if(noElivator)
            break;
      }

      model.addAttribute("stores", stores);

      if(stores.size() == 0) { // 검색 결과가 없는 경우
         model.addAttribute("errorMsg", "매장 이름을 다시 확인해 주세요.");
         return "search";
      }
      else
         return "list";
   }
}