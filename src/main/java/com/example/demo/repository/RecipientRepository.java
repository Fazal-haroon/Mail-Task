package com.example.demo.repository;

import com.example.demo.entity.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface RecipientRepository extends JpaRepository<Recipient, String> {

    List<Recipient> findBySent(boolean sent);

    @Modifying
    @Query("UPDATE Recipient u SET u.sent = :flagValue WHERE u.id IN :userIds")
    int updateFlagForUsers(@Param("flagValue") boolean flagValue, @Param("userIds") List<Long> userIds);

}