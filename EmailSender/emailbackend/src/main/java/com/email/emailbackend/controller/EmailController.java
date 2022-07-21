package com.email.emailbackend.controller;

import com.email.emailbackend.EmailModel.EmailModel;
import com.email.emailbackend.EmailModel.EmailResponse;
import com.email.emailbackend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmailController {
    @Autowired
    EmailService emailService;

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<EmailResponse> SendEmail(@RequestBody EmailModel emailModel){
        boolean result =emailService.sendEmail(emailModel.getTo(),emailModel.getSubject(),emailModel.getMessage());

        if(result){
            return new ResponseEntity<>(new EmailResponse("Successful"),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new EmailResponse("Email not sent"),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
