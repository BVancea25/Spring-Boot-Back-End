package com.example.jobproject.repository;

import com.example.jobproject.Models.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application,Integer> {
}
