package com.example.jobproject.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data //setters and getters
@Entity(name = "User")
@NoArgsConstructor
@Table(name="Users",
        uniqueConstraints = {
        @UniqueConstraint(name = "user_email_phone_unique",columnNames = {"email","phone"})
        }
)
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;
    @Column(name = "first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name = "email")
    private String email;
    @Column(name="password")
    private String password;

    @Column(name = "cv_path")
    private String cvPath;

    @Column(name="phone")
    private String phone;


}
