package com.rca.mis.onlinesubmissionmis.dao;

import com.rca.mis.onlinesubmissionmis.models.Class;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class ClassDAO extends GenericDAO<Class> {
    private static EntityManager entityManager = null;

    public ClassDAO(EntityManager entityManager) {
        super(Class.class);
        ClassDAO.entityManager = entityManager;
    }

    public static Optional<Class> findByName(String name) {
        try {
            TypedQuery<Class> query = entityManager.createQuery(
                    "SELECT c FROM Class c WHERE c.name = :name", Class.class);
            query.setParameter("name", name);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
