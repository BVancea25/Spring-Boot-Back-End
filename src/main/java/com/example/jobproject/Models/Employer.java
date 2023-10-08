package com.example.jobproject.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Employer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Employers",uniqueConstraints = {@UniqueConstraint(name = "unique_cui",columnNames = "cui")})
public class Employer {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer employerId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_description")
    private String companyDescription;

    @Column(name="cui")
    private String CUI;



}
