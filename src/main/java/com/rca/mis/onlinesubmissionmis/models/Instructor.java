package com.rca.mis.onlinesubmissionmis.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "instructors")
@DiscriminatorValue("INSTRUCTOR")
public class Instructor extends User {
    @ManyToOne
    @JoinColumn(name = "departmentName_id", nullable = false)
    private Department departmentName;

    public Instructor(){}
    public Instructor(Department departmentName) {
        this.departmentName = departmentName;
    }

    public Instructor(String firstName, String lastName, String email, String password, LocalDateTime createdAt, Department departmentName) {
        super(firstName, lastName, email, password, createdAt);
        this.departmentName = departmentName;
    }
    // Getters and Setters
    public Department getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(Department departmentName) {
        this.departmentName = departmentName;
    }
}
