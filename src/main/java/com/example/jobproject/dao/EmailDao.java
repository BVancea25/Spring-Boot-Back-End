package com.example.jobproject.dao;

import com.example.jobproject.Models.Job;
import com.example.jobproject.Models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmailDao {
    private final EntityManager em;

    public EmailDao(EntityManager em) {
        this.em = em;
    }

    public List<String> getAllEmails(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> cq = cb.createQuery(String.class);

        Root<User> userRoot = cq.from(User.class);


        cq.select(userRoot.get("email"));


        return em.createQuery(cq).getResultList();
    }
}
