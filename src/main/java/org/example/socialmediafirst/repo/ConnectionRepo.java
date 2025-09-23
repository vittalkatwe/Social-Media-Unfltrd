package org.example.socialmediafirst.repo;

import org.example.socialmediafirst.entities.Connections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepo extends JpaRepository<Connections,Long> {

    @Query("select C from Connections C where C.requestSentBy=?1 and C.userId2=?2")
    Connections findByUserId1AndUserId2(Long userId1, Long userId2);
}
