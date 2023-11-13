package com.example.jobproject.dto;

import lombok.Data;

@Data
public class UserApplicationDTO {
    private String userEmail;
    private String date;
    private String status;

    public UserApplicationDTO(String userEmail,String date,String status){
        this.userEmail=userEmail;
        this.date=date;
        this.status=status;
    }
}
