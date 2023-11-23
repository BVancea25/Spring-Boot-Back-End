package com.example.jobproject.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class JobDetailsDTO {
    private Integer jobId;
    private String jobTitle;
    private String jobRequirements;
    private String jobDescription;
    private String companyName;
    private Date date;


    public JobDetailsDTO(Integer jobId,String jobTitle,String jobRequirements,String jobDescription,Date date ,String companyName){
        this.companyName=companyName;
        this.jobId=jobId;
        this.jobTitle=jobTitle;
        this.jobRequirements=jobRequirements;
        this.jobDescription=jobDescription;
        this.date=date;
    }
}