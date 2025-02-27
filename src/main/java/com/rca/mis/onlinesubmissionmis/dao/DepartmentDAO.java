package com.rca.mis.onlinesubmissionmis.dao;

import com.rca.mis.onlinesubmissionmis.models.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class DepartmentDAO extends GenericDAO<Department> {
    private EntityManager entityManager;

    public DepartmentDAO(EntityManager entityManager) {
        super(Department.class);
        this.entityManager = entityManager;
    }

    public Optional<Department> findByName(String name) {
        try {
            TypedQuery<Department> query = entityManager.createQuery(
                    "SELECT d FROM Department d WHERE d.name = :name", Department.class);
            query.setParameter("name", name);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
