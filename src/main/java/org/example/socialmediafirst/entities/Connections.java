package org.example.socialmediafirst.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Connections {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId1;
    private Long userId2;
    private Long requestSentBy;
    private boolean status;
    private LocalDateTime connectedAt;
    private LocalDateTime requestSentAt;

    public Connections(){}

    public Connections(Long userId1, Long userId2, Long requestSentBy) {
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.status = false;
        this.requestSentBy = requestSentBy;
        this.requestSentAt=LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId1() {
        return userId1;
    }

    public void setUserId1(Long userId1) {
        this.userId1 = userId1;
    }

    public Long getUserId2() {
        return userId2;
    }

    public void setUserId2(Long userId2) {
        this.userId2 = userId2;
    }

    public Long getRequestSentBy() {
        return requestSentBy;
    }

    public void setRequestSentBy(Long requestSentBy) {
        this.requestSentBy = requestSentBy;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getConnectedAt() {
        return connectedAt;
    }

    public void setConnectedAt(LocalDateTime connectedAt) {
        this.connectedAt = connectedAt;
    }

    public LocalDateTime getRequestSentAt() {
        return requestSentAt;
    }

    public void setRequestSentAt(LocalDateTime requestSentAt) {
        this.requestSentAt = requestSentAt;
    }
}
