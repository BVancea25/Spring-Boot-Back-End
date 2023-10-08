package com.example.jobproject.service;

import com.example.jobproject.Models.Employer;
import com.example.jobproject.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.script.ScriptException;
import java.sql.SQLException;
import java.util.List;

@Service
public class EmployerService {

    @Autowired
    private EmployerRepository repository;

    public Employer saveEmployer(Employer employer){
        return repository.save(employer);
    }

    public List<Employer> getAllEmployers(){
        return repository.findAll();
    }

    public Employer getEmployerById(int id){
        return repository.findById(id).orElse(null);
    }

    public Employer getEmployerByName(String name){
        return repository.findEmployerByCompanyName(name);
    }

    public String deleteEmployerById(Integer id){
            repository.deleteById(id);
            return "Employer deleted!";
    }

    public Employer updateEmployer(Employer employer){
        Employer targetEmployer=repository.findById(employer.getEmployerId()).orElse(null);
        targetEmployer.setCompanyDescription(employer.getCompanyDescription());
        targetEmployer.setCompanyName(employer.getCompanyName());
        targetEmployer.setCUI(employer.getCUI());
        return repository.save(targetEmployer);

    }
}
