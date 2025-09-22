package org.example.socialmediafirst.entities;

import jakarta.persistence.*;
import org.example.socialmediafirst.model.AppUser;

@Entity
@Table(name = "user_ratings")
public class UserRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User giving the rating
    @ManyToOne
    @JoinColumn(name = "rater_user_id")
    private AppUser raterUser;

    // User receiving the rating
    @ManyToOne
    @JoinColumn(name = "rated_user_id")
    private AppUser ratedUser;

    // Rating from 1 to 10
    private int ratingValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getRaterUser() {
        return raterUser;
    }

    public void setRaterUser(AppUser raterUser) {
        this.raterUser = raterUser;
    }

    public AppUser getRatedUser() {
        return ratedUser;
    }

    public void setRatedUser(AppUser ratedUser) {
        this.ratedUser = ratedUser;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }
}
