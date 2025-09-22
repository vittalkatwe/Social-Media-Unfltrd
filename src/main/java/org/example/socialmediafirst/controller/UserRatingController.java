package org.example.socialmediafirst.controller;

import org.example.socialmediafirst.entities.UserRating;
import org.example.socialmediafirst.model.AppUser;
import org.example.socialmediafirst.repo.RatingRepo;
import org.example.socialmediafirst.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rating")
public class UserRatingController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RatingRepo ratingRepo;

    @GetMapping("/get")
    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @PostMapping("/rate")
    public UserRating ratingUser(@RequestParam String toUsername, @RequestParam int rating) {
        UserRating userRating = new UserRating();
        AppUser fromUser = userRepo.findByEmail(getCurrentUsername()).orElse(null);
        AppUser toUser = userRepo.findByEmail(toUsername).orElse(null);
        userRating.setRaterUser(fromUser);
        userRating.setRatedUser(toUser);
        userRating.setRatingValue(rating);
        System.out.println(ratingRepo.getUserRatingByRatedUser(toUser.getEmail()));
        return userRating;
    }
}
