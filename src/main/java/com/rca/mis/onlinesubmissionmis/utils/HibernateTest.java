package com.rca.mis.onlinesubmissionmis.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateTest {
    public static void main(String[] args) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("AssignmentSubmissionPU");
            EntityManager em = emf.createEntityManager();
            System.out.println("✅ Hibernate is configured correctly!");
            em.close();
            emf.close();
        } catch (Exception e) {
            System.err.println("❌ Hibernate configuration error: " + e.getMessage());
        }
    }
}
