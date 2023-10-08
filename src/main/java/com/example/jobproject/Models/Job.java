package com.example.jobproject.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity(name = "Job")
@Table(name = "Jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer jobId;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;



   @Column(name = "title")
    private String jobTitle;

   @Column(name = "description")
    private String jobDescription;

   @Column(name="requirements")
   private String jobRequirements;

   @Temporal(TemporalType.DATE)
   @Column(name="post_date")
    private Date date;
}
