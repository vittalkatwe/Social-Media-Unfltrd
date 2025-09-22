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

    @Autowired
    private UserController userController;

    @PostMapping("/rate")
    public UserRating ratingUser(@RequestParam String toUsername, @RequestParam int rating) {
        if(rating<=0 || rating>10) throw new RuntimeException("rating must be between 1 and 10");
        UserRating userRating = new UserRating();
        AppUser fromUser = userRepo.findByEmail(userController.getCurrentUsername()).orElse(null);
        AppUser toUser = userRepo.findByEmail(toUsername).orElse(null);
        if(fromUser.getEmail().equals(toUser.getEmail())) throw new RuntimeException("Cannot rate yourself");
        int flag=0;
        List<UserRating> firstRatings= ratingRepo.findAll();
        System.out.println(firstRatings);
        for(UserRating first:firstRatings){
            if(first.getRaterUser().getEmail().equals(fromUser.getEmail()) && first.getRatedUser().getEmail().equals(toUser.getEmail())){
                UserRating changeRating = ratingRepo.getUserRatingByRaterUserAndRatedUser(fromUser.getEmail(), toUsername);
                changeRating.setRatingValue(rating);
                ratingRepo.save(changeRating);
                flag=1;
                userRating.setRaterUser(fromUser);
                userRating.setRatedUser(toUser);
                userRating.setRatingValue(rating);
                break;
            }
        }
        if(flag==0) {
            userRating.setRaterUser(fromUser);
            userRating.setRatedUser(toUser);
            userRating.setRatingValue(rating);
            ratingRepo.save(userRating);
        }

        System.out.println(ratingRepo.getUserRatingByRatedUser(toUser.getEmail()));
        List<UserRating> allRatings= ratingRepo.getUserRatingByRatedUser(toUser.getEmail());
        int cnt=0;
        for(UserRating u : allRatings) cnt+=u.getRatingValue();
        toUser.setAvgUserRating(cnt/allRatings.size());
        userRepo.save(toUser);
        return userRating;
    }
}
