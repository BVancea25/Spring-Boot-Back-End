package com.example.jobproject.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity(name = "Application")
@Table(name = "Applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer applicationId;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @Column(name = "status")
    private String status;

    @Column(name = "cover_letter",length = 2000)
    private String coverLetter;

   @JoinColumn(name = "job_id")
    @ManyToOne
    private Job job;
}
