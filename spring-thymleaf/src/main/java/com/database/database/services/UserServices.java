package com.database.database.services;

import com.database.database.model.Users;
import com.database.database.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserServices {
    private static  final Logger log = LoggerFactory.getLogger(UserServices.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    public String registration(Model model, Users users) {
        if(userRepository.findAll().isEmpty()){
            log.info("Table empty creating a demo user...");
            userRepository.save(new Users("nagendra","nagendra@test.com",2323232,bCryptPasswordEncoder.encode("test123"),"ADMIN"));
            log.info("Create the user successfully");

        }
        else{
            users.setRole("NORMAL");
            users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
            userRepository.save(users);
            log.info("Created a new user with name ={} email={},number={}",users.getUsername(),users.getEmail(),users.getNumber());
        }
        return "/index";
    }
}
