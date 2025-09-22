package org.example.socialmediafirst.model;

import jakarta.persistence.*;
import org.example.socialmediafirst.entities.Comment;
import org.example.socialmediafirst.entities.Post;
import org.example.socialmediafirst.entities.UserRating;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String password;

    private String username;
    private String otp;
    private LocalDateTime otpExpiry;
    private LocalDateTime createdAt;
    private LocalDateTime verifiedAt;
    private LocalDateTime lastLoginAt;

    private boolean enabled = false;
    private String bio;
    private String profilePicUrl;

    // Location for "nearby users" feature
    private Double latitude;
    private Double longitude;

    // Relationships
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    private int avgUserRating;


    public AppUser() {}

    public AppUser(Long id, String email, String password, String username, LocalDateTime createdAt, LocalDateTime verifiedAt, LocalDateTime lastLoginAt, String bio, String profilePicUrl, Double latitude, Double longitude, List<Post> posts, List<Comment> comments, int avgUserRating) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.createdAt = createdAt;
        this.verifiedAt = verifiedAt;
        this.lastLoginAt = lastLoginAt;
        this.bio = bio;
        this.profilePicUrl = profilePicUrl;
        this.latitude = latitude;
        this.longitude = longitude;
        this.posts = posts;
        this.comments = comments;
        this.avgUserRating = avgUserRating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getOtpExpiry() {
        return otpExpiry;
    }

    public void setOtpExpiry(LocalDateTime otpExpiry) {
        this.otpExpiry = otpExpiry;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getVerifiedAt() {
        return verifiedAt;
    }

    public void setVerifiedAt(LocalDateTime verifiedAt) {
        this.verifiedAt = verifiedAt;
    }

    public LocalDateTime getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(LocalDateTime lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
