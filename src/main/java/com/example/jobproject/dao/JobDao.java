package com.example.jobproject.dao;

import com.example.jobproject.Models.Job;
import com.example.jobproject.Models.User;
import com.example.jobproject.dto.EmployerApplicationDTO;
import com.example.jobproject.dto.JobDTO;
import com.example.jobproject.dto.JobDetailsDTO;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JobDao {
    private final EntityManager em;

    public JobDao(EntityManager em) {
        this.em = em;
    }
    public JobDetailsDTO getJobDetails(Integer jobId) {
        if (jobId == null) {
            return null;
        }

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<Job> jobRoot = cq.from(Job.class);
        Join<Job, User> employerJoin = jobRoot.join("user");

        cq.multiselect(
                jobRoot.get("jobId"),
                jobRoot.get("jobTitle"),
                jobRoot.get("jobRequirements"),
                jobRoot.get("jobDescription"),
                jobRoot.get("date"),
                employerJoin.get("companyName")
        );

        Predicate condition = cb.equal(jobRoot.get("jobId"), jobId);

        cq.where(condition);

        List<JobDetailsDTO> results = new ArrayList<>();
        List<Tuple> tupleResult = em.createQuery(cq).getResultList();

        for (Tuple tuple : tupleResult) {
            JobDetailsDTO dto = new JobDetailsDTO(
                    (Integer) tuple.get(0),
                    (String) tuple.get(1),
                    (String) tuple.get(2),
                    (String) tuple.get(3),
                    (Date) tuple.get(4),
                    (String) tuple.get(5)
            );
            results.add(dto);
        }

        return results.isEmpty() ? null : results.get(0);
    }

    public List<JobDTO> getJobs(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<Job> jobRoot = cq.from(Job.class);
        Join<Job, User> employerJoin = jobRoot.join("user");

        cq.multiselect(
                jobRoot.get("jobId"),
                jobRoot.get("jobTitle"),
                jobRoot.get("jobRequirements"),
                employerJoin.get("companyName")
        );

        List<JobDTO> results= new ArrayList<>();
        List<Tuple> tupleResult = em.createQuery(cq).getResultList();

        for (Tuple tuple : tupleResult) {
            JobDTO dto = new JobDTO(
                    (Integer) tuple.get(0),
                    (String) tuple.get(1),
                    (String) tuple.get(2),
                    (String) tuple.get(3)


            );
            results.add(dto);
        }

        return results;
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
