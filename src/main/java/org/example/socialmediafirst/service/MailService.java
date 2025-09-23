package org.example.socialmediafirst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public String sendOtp(String toEmail) {
        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Verify your account");
        message.setText("Your OTP is: " + otp);

        mailSender.send(message);

        return otp;
    }

    public String sendConnectionRequest(Long fromId, Long toId) {
        String email="vittalkatwe@gmail.com";
        String connectionApi="http://localhost:8080/api/user/accept/from/"+fromId+"/"+toId;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Verify your account");
        message.setText("Your connection api is: " + connectionApi);
        mailSender.send(message);
        return connectionApi;
    }
}

