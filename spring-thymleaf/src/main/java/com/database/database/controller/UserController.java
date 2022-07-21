package com.database.database.controller;

import com.database.database.model.Users;
import com.database.database.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserServices userServices;


    @GetMapping("/forget-password")
    public  String forgetPassword(){
        return "forget-password";
    }
    @GetMapping("/otp")
    public String insert_otp(){
        return "/insert-otp";
    }
    @GetMapping("/updatepassword")
    public String newpass(){
        return "/newpassword";

    }


    @GetMapping("/newLogin")
    public String Login(){
        log.info("Custom login is called");
        return "customlogin";
    }
    @GetMapping("/customLogout")
    public String Logout(){
        log.info("Logging out");
        return "customlogout";
    }
    @PostMapping("/registration")
    public String Registration(Model model , @ModelAttribute Users users ) {
        log.info("Registration Process Started");
        return userServices.registration(model,users);


    }



}

