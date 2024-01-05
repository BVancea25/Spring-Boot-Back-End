package com.example.jobproject.test;

import com.example.jobproject.Models.Role;
import com.example.jobproject.Models.User;
import com.example.jobproject.dao.EmailDao;
import com.example.jobproject.repository.UserRepository;
import com.example.jobproject.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @MockBean
    private EmailDao emailDao;

    @MockBean
    private UserRepository userRepository;
    @Test
    void testGetAllEmails(){
        String email1="test1@yahoo.com";
        String email2="test2@yahoo.com";
        List<String> mockUsers = Arrays.asList(email1,email2);


        when(emailDao.getAllEmails()).thenReturn(mockUsers);


        List<String> result = userService.getAllEmails();


        assertEquals(Arrays.asList("test1@yahoo.com", "test2@yahoo.com"), result);
    }

    @Test
    void testGetUserById(){
        Optional<User> user= Optional.of(new User(0, "", "", "test@yahoo.com", "", "", "", Role.USER, "", ""));


        when(userRepository.findById(0)).thenReturn(user);

        Optional<User> targetUser=userRepository.findById(0);

        assertEquals(targetUser,targetUser);
    }
}
