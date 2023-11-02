package com.example.jobproject.dao;

import com.example.jobproject.Models.Job;
import com.example.jobproject.Models.User;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JobDao {
    private final EntityManager em;

    public JobDao(EntityManager em) {
        this.em = em;
    }

    public List<Job> findJobsByEmployerEmail(String employerEmail) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Job> cq = cb.createQuery(Job.class);
        Root<Job> jobRoot = cq.from(Job.class);

        Join<Job, User> employerJoin = jobRoot.join("user"); // "employer" is the field name in the Job entity that represents the relationship

        Predicate condition = cb.equal(employerJoin.get("email"), employerEmail);

        if (!StringUtils.isEmpty(employerEmail)) {
            cq.where(condition);
        }

        return em.createQuery(cq).getResultList();
    }
}
