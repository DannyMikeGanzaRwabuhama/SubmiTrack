package com.rca.mis.onlinesubmissionmis.models;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "submissions")
public class Submission {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    private String filePath;
    private LocalDateTime submittedAt;
    private String feedback;
    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    public Submission() {}
    public Submission(String filePath, LocalDateTime submittedAt, String feedback, Assignment assignment, Student student) {
        this.filePath = filePath;
        this.submittedAt = submittedAt;
        this.feedback = feedback;
        this.assignment = assignment;
        this.student = student;
    }
    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
