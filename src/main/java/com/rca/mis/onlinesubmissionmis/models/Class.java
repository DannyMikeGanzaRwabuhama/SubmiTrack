package com.rca.mis.onlinesubmissionmis.models;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "classes")
public class Class {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    private String name;
    @OneToMany(mappedBy = "className")
    private List<Student> students;

    @OneToMany(mappedBy = "className")
    private List<Course> courses;

    public Class() {}

    public Class(UUID id, String name, List<Student> students, List<Course> courses) {
        this.id = id;
        this.name = name;
        this.students = students;
        this.courses = courses;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
