package com.example.demo.repository;

import com.example.demo.entity.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface RecipientRepository extends JpaRepository<Recipient, String> {

    List<Recipient> findBySent(boolean sent);

    @Modifying
    @Query(value = "UPDATE recipient u SET u.sent = true WHERE u.id IN (:userIds)", nativeQuery = true)
    void updateFlagForUsers(@Param("userIds") List<Long> userIds);

    @Query(value = "SELECT * FROM recipient u WHERE u.email in (:email)",nativeQuery = true)
    List<Recipient> searchByEmail(Set<String> email);

}