package com.rca.mis.onlinesubmissionmis.models;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Class className;
    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;
    @OneToMany(mappedBy = "course")
    private List<Assignment> assignments;

    public Course() {}

    public Course(UUID id, String name, Class className, Instructor instructor, List<Assignment> assignments) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.instructor = instructor;
        this.assignments = assignments;
    }
    // Getters and Setters
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

    public Class getClassName() {
        return className;
    }

    public void setClassName(Class className) {
        this.className = className;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }
}
