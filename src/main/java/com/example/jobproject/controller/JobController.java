package com.example.jobproject.controller;

import com.example.jobproject.Models.Job;
import com.example.jobproject.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class JobController {
    @Autowired
    private JobService service;

    @GetMapping("/job")
    public List<Job> getAllJobs(){
        return service.getJobs();
    }

    @GetMapping("/job/{id}")
    public Job getJob(@PathVariable("id") Integer id){
        return service.getJob(id);
    }

    @PutMapping("/job")
    public Job updateJob(@RequestBody Job job){
        return service.updateJob(job);
    }

    @PostMapping(value="/job", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Job addJob(@RequestBody Job job){
        return service.saveJob(job);
    }

    //@DeleteMapping("/job/{id}")
    //public String deleteJob(@PathVariable Integer id){
        //return service.deleteJob(id);
    //}



}
