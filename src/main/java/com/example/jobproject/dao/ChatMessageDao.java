package com.example.jobproject.dao;

import com.example.jobproject.Models.ChatMessage;
import com.example.jobproject.Models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatMessageDao {
    private final EntityManager em;

    public ChatMessageDao(EntityManager em) {
        this.em = em;
    }

    public List<ChatMessage> getMessagesOfConversation(String receiverEmail,String senderEmail){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ChatMessage> cq = cb.createQuery(ChatMessage.class);

        Root<ChatMessage> messageRoot = cq.from(ChatMessage.class);

        Predicate senderReceiverPred=cb.and(
          cb.equal(messageRoot.get("senderEmail"),senderEmail),
                cb.equal(messageRoot.get("receiverEmail"),receiverEmail)

        );

        Predicate receiverSenderPred=cb.and(
                cb.equal(messageRoot.get("senderEmail"),receiverEmail),
                cb.equal(messageRoot.get("receiverEmail"),senderEmail)
        );

        Predicate finalPred=cb.or(senderReceiverPred,receiverSenderPred);

        cq.where(finalPred);

        return em.createQuery(cq).getResultList();
    }
}
