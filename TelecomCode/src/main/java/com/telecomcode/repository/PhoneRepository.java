package com.telecomcode.repository;

import com.telecomcode.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

    @Query("select p from Phone p where p.phoneNumber = :phoneNumber")
    Phone findByPhoneNumber(@Param(value = "phoneNumber") String phoneNumber);

    @Modifying
    @Query("update Phone p set p.isActivate = true where p.phoneNumber = :phoneNumber")
    void updateByIsActivateTrue(@Param(value = "phoneNumber") String phoneNumber);
}
