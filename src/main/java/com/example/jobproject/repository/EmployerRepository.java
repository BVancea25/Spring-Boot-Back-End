package com.example.jobproject.repository;

import com.example.jobproject.Models.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepository extends JpaRepository<Employer,Integer> {

    Employer findEmployerByCompanyName(String companyName);
}
