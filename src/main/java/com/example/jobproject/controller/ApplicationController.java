package com.example.jobproject.controller;

import com.example.jobproject.Models.Application;
import com.example.jobproject.Models.User;
import com.example.jobproject.dto.EmployerApplicationDTO;
import com.example.jobproject.dto.UserApplicationJobDTO;
import com.example.jobproject.service.ApplicationService;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/application/user")
    public List<UserApplicationJobDTO> getUserApplications(){
        return service.getApplicationsOfUser();
    }

    @PostMapping(value="/application/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Application postApplication(@RequestBody Application application,@PathVariable("id") Integer jobId){
        return service.saveApplication(application,jobId);
    }

    @GetMapping("/application/employer")
    public List<EmployerApplicationDTO> getEmployerApplications(){
        return service.getApplicationsOfEmployer();
    }

    @DeleteMapping("/application/{id}")
    public String deleteApplication(@PathVariable Integer id){
        return service.deleteApplication(id);
    }
}
