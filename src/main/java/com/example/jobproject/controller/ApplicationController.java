package com.example.jobproject.controller;

import com.example.jobproject.Models.Application;
import com.example.jobproject.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ApplicationController {
    @Autowired
    private ApplicationService service;


    @GetMapping("/application/{id}")
    public Application getApplication(@PathVariable Integer id){
        return service.getApplicationById(id);
    }

    @GetMapping("/application")
    public List<Application> getApplications(){
        return service.getApplications();
    }


    @PostMapping(value="/application")
    public Application postApplication(@RequestBody Application application,@RequestParam Integer jobId){
        return service.saveApplication(application,jobId);
    }

    @DeleteMapping("/application/{id}")
    public String deleteApplication(@PathVariable Integer id){
        return service.deleteApplication(id);
    }
}
