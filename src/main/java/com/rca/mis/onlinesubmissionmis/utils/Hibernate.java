package com.rca.mis.onlinesubmissionmis.utils;




import com.rca.mis.onlinesubmissionmis.models.Course;
import com.rca.mis.onlinesubmissionmis.models.Student;
import com.rca.mis.onlinesubmissionmis.models.Instructor;
import com.rca.mis.onlinesubmissionmis.models.Submission;
import com.rca.mis.onlinesubmissionmis.models.Assignment;
import com.rca.mis.onlinesubmissionmis.models.Class;
import com.rca.mis.onlinesubmissionmis.models.Department;
import com.rca.mis.onlinesubmissionmis.models.Notification;
import com.rca.mis.onlinesubmissionmis.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class Hibernate {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            synchronized (Hibernate.class) { // Thread safety
                if (sessionFactory == null) {
                    try {
                        Configuration configuration = new Configuration();

                        Properties settings = new Properties();
                        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/Rcamis_db?useSSL=false&serverTimezone=UTC");
                        settings.put(Environment.USER, "root");
                        settings.put(Environment.PASS, "");

                        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                        settings.put(Environment.SHOW_SQL, "true");
                        settings.put(Environment.FORMAT_SQL, "true");
                        settings.put(Environment.HBM2DDL_AUTO, "update"); // Automatically updates schema

                        configuration.setProperties(settings);

                        // Register entity classes
                        configuration.addAnnotatedClass(Student.class);
                        configuration.addAnnotatedClass(Course.class);
                        configuration.addAnnotatedClass(Instructor.class);
                        configuration.addAnnotatedClass(Submission.class);
                        configuration.addAnnotatedClass(Notification.class);
                        configuration.addAnnotatedClass(User.class);
                        configuration.addAnnotatedClass(Assignment.class);
                        configuration.addAnnotatedClass(Class.class);
                        configuration.addAnnotatedClass(Department.class);



                        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                                .applySettings(configuration.getProperties())
                                .build();

                        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("Error initializing Hibernate session factory.");
                    }
                }
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}

