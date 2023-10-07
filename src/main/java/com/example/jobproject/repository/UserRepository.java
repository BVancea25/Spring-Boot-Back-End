package com.example.jobproject.repository;

import com.example.jobproject.Models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByLastName(String lastName);


    @Modifying
    @Transactional
    @Query("DELETE from User where userID=:id")
    int deleteByIdd(@Param("id") int id);
}
