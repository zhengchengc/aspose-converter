package com.assessment.management.service;

import com.assessment.management.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    Student createStudent(Student student);
    void deleteStudent(Long id);
    Student updateStudent(Long id, Student student);
}
