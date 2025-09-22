package org.example.socialmediafirst.repo;

import org.example.socialmediafirst.entities.UserRating;
import org.example.socialmediafirst.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepo extends JpaRepository<UserRating, Long> {

    @Query("select UR from UserRating UR where UR.ratedUser.email=?1")
    List<UserRating> getUserRatingByRatedUser(String email);
}
