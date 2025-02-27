package com.rca.mis.onlinesubmissionmis.dao;

import com.rca.mis.onlinesubmissionmis.models.Notification;
import com.rca.mis.onlinesubmissionmis.utils.HibernateUtil;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.UUID;

public class NotificationDAO extends GenericDAO<Notification> {
    public NotificationDAO() {
        super(Notification.class);
    }
    public List<Notification> getNotificationByClass(UUID classId){
        EntityManager em = HibernateUtil.getEntityManager();
        try{
            return em.createQuery("SELECT n FROM Notification n WHERE n.className.id = :classId ORDER BY n.date DESC", Notification.class)
                    .setParameter("classId", classId)
                    .getResultList();
        }finally {
            em.close();
        }
    }
}
