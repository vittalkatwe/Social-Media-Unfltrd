package org.example.socialmediafirst.controller;


import org.example.socialmediafirst.entities.Connections;
import org.example.socialmediafirst.model.AppUser;
import org.example.socialmediafirst.repo.ConnectionRepo;
import org.example.socialmediafirst.repo.UserRepo;
import org.example.socialmediafirst.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private MailService mailService;

    @Autowired
    private ConnectionRepo connectionRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/get")
    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @PostMapping("/addcoordinates")
    public String addCoordinates(@RequestParam double latitude, @RequestParam double longitude) {
        String userEmail=getCurrentUsername();
        AppUser user=userRepo.findByEmail(userEmail).orElse(null);
        if(user==null) return "User not found";
        user.setLatitude(latitude);
        user.setLongitude(longitude);
        userRepo.save(user);
        return "Latitude and Longitude added";
    }

    @PostMapping("/connection")
    public String addConnection(@RequestParam String toUserEmail) {
        AppUser toUser=userRepo.findByEmail(toUserEmail).orElse(null);
        String userEmail=getCurrentUsername();
        AppUser fromUser=userRepo.findByEmail(userEmail).orElse(null);
        Connections uConnection=connectionRepo.findByUserId1AndUserId2(fromUser.getId(),toUser.getId());
        if(uConnection!=null) { return "Cannot send multiple connections"; }
        Connections userConnection=new Connections(fromUser.getId(), toUser.getId(), fromUser.getId());
        connectionRepo.save(userConnection);
        mailService.sendConnectionRequest(fromUser.getId(),toUser.getId());
        return "Connection request sent.";
    }

    @PostMapping("accept/from/{fromId}/{toId}")
    public String acceptConnection(@PathVariable Long fromId, @PathVariable Long toId) {
        Connections userConnection=connectionRepo.findByUserId1AndUserId2(fromId,toId);
        if(userConnection==null) {
            return "Connection not valid";
        }
        if(userConnection.isStatus()==true) return "Connection is already accepted";
        userConnection.setConnectedAt(LocalDateTime.now());
        userConnection.setStatus(true);
        connectionRepo.save(userConnection);
        return "Connection accepted";
    }


}
