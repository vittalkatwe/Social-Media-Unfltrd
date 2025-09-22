package org.example.socialmediafirst.entities;

import jakarta.persistence.*;
import org.example.socialmediafirst.model.AppUser;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Owner of the post
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    private String caption;

    // PHOTO, VIDEO, STORY_PHOTO, STORY_VIDEO
    @Enumerated(EnumType.STRING)
    private PostType postType;

    private String mediaUrl;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime expiresAt;  // only for story

    // Likes
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostLike> likes;

    // Comments
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    // Auto set expiry for stories
    public void markAsStory() {
        if (postType == PostType.STORY_PHOTO || postType == PostType.STORY_VIDEO) {
            this.expiresAt = createdAt.plusHours(24);
        }
    }
}

