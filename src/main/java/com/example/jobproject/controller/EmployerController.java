package com.example.jobproject.controller;

import com.example.jobproject.Models.Employer;
import com.example.jobproject.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployerController {
    @Autowired
    private EmployerService service;

    @PostMapping("/employer")
    public Employer addEmployer(@RequestBody Employer employer){
        return service.saveEmployer(employer);
    }

    @GetMapping("/employer")
    public List<Employer> getAllEmployers(){
        return service.getAllEmployers();
    }

    @GetMapping("/employerById/{id}")
    public Employer getEmployerById(@PathVariable Integer id){
        return service.getEmployerById(id);
    }

    @GetMapping("/employer/{name}")
    public Employer getEmployerByName(@PathVariable String name){
        return service.getEmployerByName(name);
    }

    @DeleteMapping("/employer/{id}")
    public String deleteEmployer(@PathVariable Integer id){
        return service.deleteEmployerById(id);
    }

    @PutMapping("/employer")
    public Employer updateEmployer(@RequestBody Employer employer ){
        return service.updateEmployer(employer);
    }
}
