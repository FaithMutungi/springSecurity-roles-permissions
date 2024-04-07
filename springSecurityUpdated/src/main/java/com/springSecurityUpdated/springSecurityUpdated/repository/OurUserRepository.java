package com.springSecurityUpdated.springSecurityUpdated.repository;

import com.springSecurityUpdated.springSecurityUpdated.model.OurUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OurUserRepository extends JpaRepository <OurUser, Integer>{
    @Query(value = "select * from our_users where email=?1", nativeQuery = true)
    Optional<OurUser> findByEmail(String email);
}
