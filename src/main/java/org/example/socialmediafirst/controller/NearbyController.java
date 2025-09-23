package org.example.socialmediafirst.controller;

import org.example.socialmediafirst.model.AppUser;
import org.example.socialmediafirst.repo.UserRepo;
import org.example.socialmediafirst.service.NearbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/nearby")
public class NearbyController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserController userController;

    @Autowired
    private NearbyService nearbyService;


    @GetMapping("/get-nearby-users")
    public List<AppUser> getNearbyUsers() {
        String userEmail=userController.getCurrentUsername();
        AppUser user=userRepo.findByEmail(userEmail).orElse(null);
        double latitude=user.getLatitude();
        double longitude=user.getLongitude();

        double radiusMeters=50;

        List<AppUser> candidates = userRepo.getAppUserByLatitudeAndLongitude(latitude, longitude);
        return nearbyService.findNearbyUsers(latitude, longitude, candidates, radiusMeters);
    }

}
