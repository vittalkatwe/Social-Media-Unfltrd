package org.example.socialmediafirst.controller;


import org.example.socialmediafirst.model.AppUser;
import org.example.socialmediafirst.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

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


}
