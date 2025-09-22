package org.example.socialmediafirst.entities;

import jakarta.persistence.*;
import org.example.socialmediafirst.model.AppUser;

import java.time.LocalDateTime;

@Entity
@Table(name = "post_likes")
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User who liked
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    // Post liked
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private LocalDateTime likedAt = LocalDateTime.now();
}

