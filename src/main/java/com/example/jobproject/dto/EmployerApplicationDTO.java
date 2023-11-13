package com.example.jobproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data

public class EmployerApplicationDTO {
    private Integer applicationId;
    private String userEmail;
    private String phone;
    private String status;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String coverLetter;
    private Date date;
    public EmployerApplicationDTO(Integer applicationId, String coverLetter, String status, String jobTitle, String firstName, String lastName, String phone, String userEmail, Date date){
        this.applicationId=applicationId;
        this.userEmail=userEmail;
        this.phone=phone;
        this.status=status;
        this.firstName=firstName;
        this.lastName=lastName;
        this.jobTitle=jobTitle;
        this.coverLetter=coverLetter;
    }

}
