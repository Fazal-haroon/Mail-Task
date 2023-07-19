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
    @Query(value = "UPDATE recipient u SET u.sent = true WHERE u.id IN (:userIds)", nativeQuery = true)
    void updateFlagForUsers(@Param("userIds") List<Long> userIds);

    @Query(value = "SELECT * FROM RECIPIENT u WHERE u.email in ?1",nativeQuery = true)
    List<Recipient> searchByEmail(List<String> email);

/*
    @Query(value = "SELECT * FROM CLASSIFICATION WHERE CLASSIFICATION_ID in ?1", nativeQuery = true)
    public List<Classification> findClassificationByclassificationId(List<Long> classifications);


    @Query(value = "SELECT count(*) FROM ENTITY_CORPORATION WHERE ENTITY_ID in(:entityIds)", nativeQuery = true)
    int totalCorporateEntityFound(List<Long> entityIds);

    @Query(value = "SELECT count(*) FROM ENTITY_GOVERNMENT WHERE ENTITY_ID in(:entityIds)", nativeQuery = true)
    int totalGovtEntity(@Param("entityIds") List<Long> enitiIds);

    @Query(value = "SELECT CLASSIFICATION_ID FROM MAINSERVICE WHERE MAIN_SERVICE_ID in ?1", nativeQuery = true)
    public List<Long> findClassificationByMainserviceId(List<Long> mainservices);

    @Query(value = "SELECT * FROM NOTARY_USER WHERE USER_NAME =:userName AND  NOTARY_USER_ID NOT IN (:empId)", nativeQuery = true)
    List<NotaryUser> findByUserNameAndEmpId(String userName,long empId);

    @Query(value = "SELECT count(*) FROM ENTITY_OFFICE WHERE ENTITY_ID in(:entityIds)", nativeQuery = true)
    int totalEntityFound(List<Long> entityIds);*/
}