package com.example.jobproject.service;

import com.example.jobproject.Models.Job;
import com.example.jobproject.Models.User;
import com.example.jobproject.dao.JobDao;
import com.example.jobproject.repository.JobRepository;
import com.example.jobproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobDao customJobDao;

    public List<Job> getJobs(){
        return repository.findAll();
    }

    public List<Job> getEmployerJobs(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String email=auth.getName();
        return customJobDao.findJobsByEmployerEmail(email);
    }

    public Job getJob(Integer id){
        return repository.findById(id).orElse(null);
    }

    public Job saveJob(Job job){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String email=auth.getName();
        User employer=userRepository.findByEmail(email).orElse(null);

        job.setUser(employer);
        job.setDate(new Date());
        return repository.save(job);

    }


    public Job updateJob(Job job){
        Job targetJob=repository.findById(job.getJobId()).orElse(null);
        assert targetJob != null;
        targetJob.setJobDescription(job.getJobDescription());
        targetJob.setJobTitle(job.getJobTitle());
        targetJob.setDate(job.getDate());
        targetJob.setJobRequirements(job.getJobRequirements());
        return repository.save(targetJob);
    }

    public String deleteJob(Integer id){
        repository.deleteById(id);
        return "Job deleted!!!";
    }
}
