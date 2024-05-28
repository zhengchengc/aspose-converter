package com.assessment.management.controller;

import com.assessment.management.entity.Student;
import com.assessment.management.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Slf4j
public class StudentController {
    
    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        List<Student> studentList = studentService.getAllStudents();
        log.info("getAllStudents: {}", studentList);
        return studentList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        log.info("getStudentById: {}", id);
        Student student = studentService.getStudentById(id);
        if (ObjectUtils.isEmpty(student)) {
            log.warn("Update student {} failed", id);
            return ResponseEntity.notFound().build();
        } else {
            log.info("Update student {} successful", id);
            return ResponseEntity.ok(student);
        }
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        try {
            log.info("Creating student {}", student);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(studentService.createStudent(student));
        } catch (Exception e) {
            log.warn("Create student {} failed", student);
            log.warn(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        log.info("Updating student {}", updatedStudent);
        Student student = studentService.updateStudent(id, updatedStudent);
        if (ObjectUtils.isEmpty(student)) {
            log.warn("Update student {} failed", updatedStudent);
            return ResponseEntity.notFound().build();
        } else {
            log.info("Update student {} successful", updatedStudent);
            return ResponseEntity.ok(student);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        log.info("Deleting student {}", id);
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
