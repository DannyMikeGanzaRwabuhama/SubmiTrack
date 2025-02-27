package com.rca.mis.onlinesubmissionmis.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "students")
@DiscriminatorValue("STUDENT")
public class Student extends User {
    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Class className;
    @Column(name = "dob", nullable = false)
    private String dob;

    public Student(){}

    public Student(String firstName, String lastName, String email, String password, LocalDateTime createdAt, Class className, String dob) {
        super(firstName, lastName, email, password, createdAt);
        this.className = className;
        this.dob = dob;
    }
    // Getters and setters
    public Class getClassName() {
        return className;
    }
    public void setClassName(Class className) {
        this.className = className;
    }
    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }
}
