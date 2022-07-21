package com.database.database.fiegnController;

import com.database.database.model.Email;
import com.database.database.model.EmailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="EMAIL-SENDER",url="http://localhost:9001/")
public interface DataConsumer {





    @GetMapping("/welcome")
    public String welcome();

    @PostMapping("/sendEmail")
    public ResponseEntity<EmailResponse> SendEmail(@RequestBody Email email);

}
