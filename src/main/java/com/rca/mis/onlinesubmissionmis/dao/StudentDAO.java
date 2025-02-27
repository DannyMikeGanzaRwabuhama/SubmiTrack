package com.rca.mis.onlinesubmissionmis.dao;

import com.rca.mis.onlinesubmissionmis.models.Student;

public class StudentDAO extends GenericDAO<Student> {
    public StudentDAO() {
        super(Student.class);
    }
}