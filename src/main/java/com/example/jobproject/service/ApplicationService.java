package com.example.jobproject.service;

import com.example.jobproject.Models.Application;
import com.example.jobproject.Models.Job;
import com.example.jobproject.Models.User;
import com.example.jobproject.config.SecurityConfig;
import com.example.jobproject.dao.ApplicationDao;
import com.example.jobproject.dto.EmployerApplicationDTO;
import com.example.jobproject.dto.UserApplicationDTO;
import com.example.jobproject.dto.UserApplicationJobDTO;
import com.example.jobproject.repository.ApplicationRepository;
import com.example.jobproject.repository.JobRepository;
import com.example.jobproject.repository.UserRepository;
import jakarta.persistence.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplicationDao applicationDao;




    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    public List<Application> getApplications(){
        return repository.findAll();
    }

    public Application getApplicationById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public List<UserApplicationJobDTO> getApplicationsOfUser(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String email=auth.getName();
        return applicationDao.findApplicationsByUser(email);
    }

    public List<EmployerApplicationDTO> getApplicationsOfEmployer(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String email=auth.getName();
        return applicationDao.findApplicationsByEmployer(email);
    }

    public Application saveApplication(Application application,Integer jobId){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String email=auth.getName();
        //logger.info(email);
        User user=userRepository.findByEmail(email).orElse(null);
        Job job=jobRepository.findById(jobId).orElse(null);
        application.setUser(user);
        application.setJob(job);
        application.setDate(new Date());
        return repository.save(application);
    }

    public Application updateApplication(Application application){
        Application targetApplication=repository.findById(application.getApplicationId()).orElse(null);
        targetApplication.setStatus(application.getStatus());
        targetApplication.setCoverLetter(application.getCoverLetter());
        return repository.save(targetApplication);
    }

    public String deleteApplication(Integer id){
        repository.deleteById(id);
        return "Application deleted!";
    }
}
