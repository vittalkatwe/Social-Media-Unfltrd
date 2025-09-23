package org.example.socialmediafirst.repo;

import org.example.socialmediafirst.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UserRepo extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);

    @Query("SELECT AU FROM AppUser AU " +
            "WHERE AU.latitude BETWEEN (?1 - 1.0) AND (?1 + 1.0) " +
            "AND AU.longitude BETWEEN (?2 - 1.0) AND (?2 + 1.0)")
    List<AppUser> getAppUserByLatitudeAndLongitude(Double latitude, Double longitude);

}
