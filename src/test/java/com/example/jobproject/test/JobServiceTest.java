package com.example.jobproject.test;

import com.example.jobproject.Models.Job;
import com.example.jobproject.Models.Role;
import com.example.jobproject.Models.User;
import com.example.jobproject.dao.JobDao;
import com.example.jobproject.dto.JobDTO;
import com.example.jobproject.repository.JobRepository;
import com.example.jobproject.repository.UserRepository;
import com.example.jobproject.service.JobService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class JobServiceTest {


    @MockBean
    private JobDao jobDao;

    @Autowired
    private JobService jobService;

    @Test
    void getJobsTest(){
        JobDTO job1=new JobDTO(0,"Baker","Be Cool","Maya Pan");
        JobDTO job2=new JobDTO(1,"Player","Not boring","Ceva");

        List<JobDTO> mockJobs = Arrays.asList(job1,job2);

        when(jobDao.getJobs()).thenReturn(mockJobs);

        List<JobDTO> result=jobService.getJobs();

        assertEquals(result,mockJobs);
    }

    @Test
    void getEmployerJobsTest(){

        User employer=new User(0,"","","employer@example.com"
                ,"","","", Role.EMPLOYER,"","");

        String employerEmail =employer.getEmail();

        Authentication authentication = new UsernamePasswordAuthenticationToken(employerEmail, "password");

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);

        Date now=new Date();

        Job job1 = new Job(0,employer,"Baker","Ceva","Altceva",now);
        Job job2 = new Job(1,employer,"Cooker","hahahihi","ye",now);
        List<Job> expectedJobs = Arrays.asList(job1, job2);


        when(jobDao.findJobsByEmployerEmail(employerEmail)).thenReturn(expectedJobs);


        List<Job> result = jobService.getEmployerJobs();


        assertEquals(expectedJobs, result);

        // Verify that the repository method was called with the correct parameter
        verify(jobDao, times(1)).findJobsByEmployerEmail(employerEmail);
    }

}
