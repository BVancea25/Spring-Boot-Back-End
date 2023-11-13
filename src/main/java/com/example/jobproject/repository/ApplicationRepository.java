package com.example.jobproject.repository;

import com.example.jobproject.Models.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application,Integer> {

}
