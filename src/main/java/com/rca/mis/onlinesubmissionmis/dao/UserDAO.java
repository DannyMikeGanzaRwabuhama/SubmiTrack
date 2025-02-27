package com.rca.mis.onlinesubmissionmis.dao;

import com.rca.mis.onlinesubmissionmis.models.User;
import com.rca.mis.onlinesubmissionmis.utils.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.Optional;

public class UserDAO extends GenericDAO<User> {
    public UserDAO() {
        super(User.class);
    }
    public Optional<User> findByEmail(String email) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getResultStream()
                    .findFirst();
        } finally {
            em.close();
        }
    }
}
