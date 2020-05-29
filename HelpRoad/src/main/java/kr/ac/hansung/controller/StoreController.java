package kr.ac.hansung.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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

   // 검색 매장 리스트
   @RequestMapping("/list")
   public String showStores(Model model, @RequestParam(value = "name", required = false) String name) {

      List<Store> stores = storeService.getCurrent(name);

      while (true) {
         boolean noElivator = true;

         for (int i = 0; i < stores.size(); i++) {
            if (stores.get(i).getName().equals("엘리베이터1")) {
               stores.remove(i);
               noElivator = false;
               break;
            }
            if (stores.get(i).getName().equals("엘리베이터2")) {
               stores.remove(i);
               noElivator = false;
               break;
            }
         }
         if (noElivator)
            break;
      }

      model.addAttribute("stores", stores);

      return "list";
   }
}