package com.example.jobproject.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity()
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employer_details")
@DiscriminatorValue("EMPLOYER")
public class Employer extends User {

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_description")
    private String companyDescription;

    @Column(name="cui")
    private String CUI;



}
