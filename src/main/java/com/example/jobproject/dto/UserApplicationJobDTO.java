package com.example.jobproject.dto;

import lombok.Data;

@Data
public class UserApplicationJobDTO {
    private String companyName;
    private String applicationStatus;
    private String jobTitle;
    private Integer applicationId;

    public UserApplicationJobDTO(String companyName, String applicationStatus, String jobTitle, Integer applicationId) {
        this.companyName = companyName;
        this.applicationStatus = applicationStatus;
        this.jobTitle = jobTitle;
        this.applicationId=applicationId;
    }

    // Getters and setters for userName, applicationStatus, and jobTitle
}
