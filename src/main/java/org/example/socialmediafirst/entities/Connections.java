package org.example.socialmediafirst.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Connections {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId1;
    private Long userId2;
    private boolean status;

    public Connections(){}

    public Connections(Long userId1, Long userId2, boolean status) {
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.status = true;
    }
}
