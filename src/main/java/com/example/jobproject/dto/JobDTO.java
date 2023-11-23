package com.example.jobproject.dto;

import lombok.Data;

@Data
public class JobDTO {
    private Integer jobId;
    private String jobTitle;
    private String jobRequirements;
    private String companyName;

    public JobDTO(Integer jobId,String jobTitle,String jobRequirements,String companyName){
        this.companyName=companyName;
        this.jobId=jobId;
        this.jobTitle=jobTitle;
        this.jobRequirements=jobRequirements;
    }
}
