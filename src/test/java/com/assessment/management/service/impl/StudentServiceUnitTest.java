package com.assessment.management.service.impl;

import com.assessment.management.entity.Student;
import com.assessment.management.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class StudentServiceUnitTest {
  
  @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        studentService = new StudentServiceImpl();
    }

    @Test
    void getAllStudents_ShouldReturnAllStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("John Doe", 20));
        students.add(new Student("Jane Smith", 22));
        when(studentRepository.findAll()).thenReturn(students);

        List<Student> result = studentService.getAllStudents();

        assertEquals(students, result);
    }

    @Test
    void getStudentById_ShouldReturnStudent() {
        Long studentId = 1L;
        Student student = new Student("John Doe", 20);
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        Student result = studentService.getStudentById(studentId);

        assertEquals(student, result);
    }

    @Test
    void getStudentById_ShouldReturnNull_WhenStudentNotFound() {
        Long studentId = 1L;
        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        Student result = studentService.getStudentById(studentId);

        assertNull(result);
    }

    @Test
    void createStudent_ShouldReturnNewStudent() {
        Student student = new Student("John Doe", 20);
        when(studentRepository.save(student)).thenReturn(student);

        Student result = studentService.createStudent(student);

        assertEquals(student, result);
    }

    @Test
    void createStudent_ShouldReturnNull_WhenExceptionThrown() {
        Student student = new Student("John Doe", 20);
        when(studentRepository.save(student)).thenThrow(new RuntimeException());

        Student result = studentService.createStudent(student);

        assertNull(result);
    }

    @Test
    void updateStudent_ShouldReturnUpdatedStudent() {
        Long studentId = 1L;
        Student existingStudent = new Student("John Doe", 20);
        Student updatedStudent = new Student("Jane Smith", 22);
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(existingStudent));
        when(studentRepository.save(existingStudent)).thenReturn(updatedStudent);

        Student result = studentService.updateStudent(studentId, updatedStudent);

        assertEquals(updatedStudent, result);
        assertEquals(updatedStudent.getName(), existingStudent.getName());
        assertEquals(updatedStudent.getAge(), existingStudent.getAge());
    }

    @Test
    void updateStudent_ShouldReturnNull_WhenStudentNotFound() {
        Long studentId = 1L;
        Student updatedStudent = new Student("Jane Smith", 22);
        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        Student result = studentService.updateStudent(studentId, updatedStudent);

        assertNull(result);
    }
}
