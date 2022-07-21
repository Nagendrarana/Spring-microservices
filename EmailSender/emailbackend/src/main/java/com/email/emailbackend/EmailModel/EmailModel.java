package com.email.emailbackend.EmailModel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailModel {
        private String to;
        private String subject;
        private String message;

}
