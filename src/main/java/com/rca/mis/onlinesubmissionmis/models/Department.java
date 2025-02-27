package com.rca.mis.onlinesubmissionmis.models;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    private String name;
    @OneToMany(mappedBy = "departmentName")
    private List<Instructor> instructors;

    public Department(){}
    public Department(UUID id, String name, List<Instructor> instructors) {
        this.id = id;
        this.name = name;
        this.instructors = instructors;
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }
}
