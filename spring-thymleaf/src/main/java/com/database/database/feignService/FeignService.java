package com.database.database.feignService;

import com.database.database.fiegnController.DataConsumer;
import com.database.database.model.Email;
import com.database.database.model.Users;
import com.database.database.model.newpassword;
import com.database.database.repository.UserRepository;
import com.database.database.services.forgetPassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class FeignService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DataConsumer dataConsumer;

    @Autowired
    private forgetPassword forgetpassword;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    Random random = new Random();
    public int otp;
    public String ForgetPassEmail;

    Logger log = LoggerFactory.getLogger(FeignService.class);

    @GetMapping("/welcome-user")
    public String demo() {
        return dataConsumer.welcome();

    }

    @PostMapping("/password-reset")
    public String sendOtp(Model model, @ModelAttribute Users users, Email email) {
        Logger log = LoggerFactory.getLogger(forgetPassword.class);

        log.info("inside the password-rest");
        System.out.println(users.getEmail());
        log.info(users.getEmail());
        ForgetPassEmail = userRepository.findByEmail(users.getEmail()).getEmail();
        if (ForgetPassEmail == null) {
            throw new UsernameNotFoundException("User not found");
        } else {
            otp = random.nextInt(1000000);
            log.info(String.valueOf(otp));
            email.setMessage("OTP is " + otp);
            email.setSubject("Reset password");
            email.setTo(users.getEmail());
            dataConsumer.SendEmail(email);
            log.info("email sent to {} ", users.getEmail());

        }


        return "<a href='/otp'>click here to confirm otp</a>";
    }

    @PostMapping("/otp-matching")
    public String otpmatching(@ModelAttribute Users users) {

        if (users.getNumber() == otp) {
            return "<a href='/updatepassword'>click here to set new password</a>";
        } else {
            return "<h1>Wrong OTP </h1>" +
                    "<a href='/forget-password'>Wrong OTP</a>";
        }


    }

    @PostMapping("/new-pass")
    public String newpass( @ModelAttribute newpassword Pass) {
        log.info("email is {} and pass is {}  {}",ForgetPassEmail,Pass.getPassoword(),Pass.getConfirm_password());

      /* userRepository.findByEmail(ForgetPassEmail).setPassword(bCryptPasswordEncoder.encode(Pass.getConfirm_password()));*/
      String username = userRepository.findByEmail(ForgetPassEmail).getUsername();
      long number  = userRepository.findByEmail(ForgetPassEmail).getNumber();
      String role = userRepository.findByEmail(ForgetPassEmail).getRole();


      userRepository.save(new Users(username,ForgetPassEmail,number,(bCryptPasswordEncoder.encode(Pass.getConfirm_password())),role));


        return "<h1>Password updated </h1>" +
                "<a href='/newLogin'>Login</a>";
    }
}







