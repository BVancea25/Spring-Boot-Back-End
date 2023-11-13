package com.example.jobproject.dao;

import com.example.jobproject.Models.Application;
import com.example.jobproject.Models.Job;
import com.example.jobproject.Models.User;
import com.example.jobproject.config.SecurityConfig;
import com.example.jobproject.dto.EmployerApplicationDTO;
import com.example.jobproject.dto.UserApplicationDTO;
import com.example.jobproject.dto.UserApplicationJobDTO;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ApplicationDao {
    private final EntityManager em;
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
    public ApplicationDao(EntityManager em) {
        this.em = em;
    }


    public List<EmployerApplicationDTO> findApplicationsByEmployer(String employerEmail){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<Application> applicationRoot = cq.from(Application.class);

        Join<Application,Job> jobJoin=applicationRoot.join("job");
        Join<Job,User> employerJoin= jobJoin.join("user");
        Join<Application,User> userJoin=applicationRoot.join("user");

        cq.multiselect(
                applicationRoot.get("applicationId"), // Replace with the actual field name from User entity
                applicationRoot.get("coverLetter"), // Replace with the actual field name from Application entity
                applicationRoot.get("status"), // Replace with the actual field name from Job entity
                jobJoin.get("jobTitle"),
                userJoin.get("firstName"),
                userJoin.get("lastName"),
                userJoin.get("phone"),
                userJoin.get("email"),
                applicationRoot.get("date")
        );

        Predicate condition=cb.equal(employerJoin.get("email"),employerEmail);

        if (!StringUtils.isEmpty(employerEmail)) {
            cq.where(condition);
        }

        List<EmployerApplicationDTO> results= new ArrayList<>();
        List<Tuple> tupleResult = em.createQuery(cq).getResultList();

        for (Tuple tuple : tupleResult) {
            EmployerApplicationDTO dto = new EmployerApplicationDTO(
                    (Integer) tuple.get(0),
                    (String) tuple.get(1),
                    (String) tuple.get(2),
                    (String) tuple.get(3),
                    (String) tuple.get(4),
                    (String) tuple.get(5),
                    (String) tuple.get(6),
                    (String) tuple.get(7),
                    (Date) tuple.get(8)

            );
            results.add(dto);
        }

        return results;
    }

    public List<UserApplicationJobDTO> findApplicationsByUser(String userEmail) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<Application> applicationRoot = cq.from(Application.class);
       // logger.info(userEmail);
        Join<Application, User> userJoin = applicationRoot.join("user");
        Join<Application, Job> jobJoin = applicationRoot.join("job");
        Join<Job, User> jobPostedByUser = jobJoin.join("user");

        cq.multiselect(
                jobPostedByUser.get("companyName"), // Replace with the actual field name from User entity
                applicationRoot.get("status"), // Replace with the actual field name from Application entity
                jobJoin.get("jobTitle"), // Replace with the actual field name from Job entity
                applicationRoot.get("applicationId")
        );

        Predicate condition = cb.equal(userJoin.get("email"), userEmail);

        if (!StringUtils.isEmpty(userEmail)) {
            cq.where(condition);
        }

        List<UserApplicationJobDTO> results = new ArrayList<>();
        List<Tuple> tupleResult = em.createQuery(cq).getResultList();

        for (Tuple tuple : tupleResult) {
            UserApplicationJobDTO dto = new UserApplicationJobDTO(
                    (String) tuple.get(0), // companyName
                    (String) tuple.get(1), // applicationStatus
                    (String) tuple.get(2),  // jobTitle
                    (Integer) tuple.get(3)   //applicationId
            );
            results.add(dto);
        }

        return results;
    }


}
