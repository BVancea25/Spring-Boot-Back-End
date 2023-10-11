package com.example.jobproject.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@AllArgsConstructor
@Data //setters and getters
@Entity(name = "User")
@NoArgsConstructor
@Table(name="Users",
        uniqueConstraints = {
        @UniqueConstraint(name = "user_email_phone_unique",columnNames = {"email","phone"})
        }
)
@DiscriminatorColumn(name="role",
        discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("USER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User implements UserDetails {
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

    @Column(name = "role",insertable = false,updatable = false)
    @Enumerated(EnumType.STRING)
    private Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
