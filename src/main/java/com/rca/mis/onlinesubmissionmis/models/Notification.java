package com.rca.mis.onlinesubmissionmis.models;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    @Column(nullable = false)
    private String message;
    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Class className;
    @Column(nullable = false)
    private LocalDateTime date;
    // Constructors

    public Notification() {}
    public Notification(UUID id, String message, Class className, LocalDateTime date) {
        this.id = id;
        this.message = message;
        this.className = className;
        this.date = date;
    }
    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Class getClassName() {
        return className;
    }

    public void setClassName(Class className) {
        this.className = className;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
