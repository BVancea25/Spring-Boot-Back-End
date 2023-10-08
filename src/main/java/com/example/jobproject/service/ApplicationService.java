package com.example.jobproject.service;

import com.example.jobproject.Models.Application;
import com.example.jobproject.Models.Job;
import com.example.jobproject.Models.User;
import com.example.jobproject.repository.ApplicationRepository;
import com.example.jobproject.repository.JobRepository;
import com.example.jobproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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



    public List<Application> getApplications(){
        return repository.findAll();
    }

    public Application getApplicationById(Integer id){
        return repository.findById(id).orElse(null);
    }

    public Application saveApplication(Application application,Integer userId,Integer jobId){
        User user=userRepository.findById(userId).orElse(null);
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
