package com.example.demo.repository;

import com.example.demo.entity.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient, String> {

    List<Recipient> findBySentOrderByCreatedAtAsc(boolean sent);

    @Modifying
    @Query(value = "UPDATE recipient u SET u.sent = :status, u.created_at = :currentTime WHERE u.id = :userId", nativeQuery = true)
    void updateRecipientStatusAndCreatedAt(Long userId, boolean status, LocalDateTime currentTime);

}