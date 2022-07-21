package com.database.database.controller;

import com.database.database.model.TutorialModel;
import com.database.database.model.Users;
import com.database.database.services.TutorialServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WEBController {
   Logger log = LoggerFactory.getLogger(WEBController.class);
    @Autowired
    TutorialServices tutorialServices;
    @GetMapping("/index")
    public  String Index(Model model){


        return tutorialServices.index(model);
    }
    @PostMapping("/index")
    public String Indexp(Model model){
        return tutorialServices.index(model);
    }

    @GetMapping("/practice")
    public String Practice( Model model){

        return tutorialServices.practice(model);
    }

    @GetMapping("/getdata")
    public String Get_data(Model model){

        return tutorialServices.get_data(model);
    }
    @GetMapping("/insert")
    public String Insert(Model model, @ModelAttribute TutorialModel tutorialModel){


        return tutorialServices.insert(model,tutorialModel);
    }
    @PostMapping("/save")
    public String Save(Model model, @ModelAttribute TutorialModel tutorialModel){

        return tutorialServices.save(model,tutorialModel);
    }
    @GetMapping("/update")
    public String Update(Model model,@ModelAttribute TutorialModel tutorialModel){

        return tutorialServices.update(model,tutorialModel);
    }
    @GetMapping("/delete")
    public String Delete(Model model,@ModelAttribute TutorialModel tutorialModel){

        return tutorialServices.delete(model,tutorialModel);
    }
    @PostMapping("/deletebyweb")
    public String Deletebyweb(Model model,@ModelAttribute TutorialModel tutorialModel){
         return tutorialServices.deletebyweb(model,tutorialModel);
    }
    @GetMapping("/")
    public String testing(Model model)
    {   log.info("Loading registration page ...");
        return "testing";

    }

    @PostMapping("/updatebyweb")
    public String Updatebyweb(Model model,@ModelAttribute  TutorialModel tutorialModel) throws Exception {
        return tutorialServices.updatebyweb(model,tutorialModel);
    }



}
