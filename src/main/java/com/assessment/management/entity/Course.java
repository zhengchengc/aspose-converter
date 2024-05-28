package com.assessment.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "id")
    private Long id;

    @Column(nullable = false, name = "course_name")
    private String courseName;

    @Column(name = "course_description")
    private String courseDescription;

    @Column(nullable = false, name = "course_code")
    private String courseCode;

    @OneToMany(mappedBy = "course")
    Set<CourseRegistration> registrations;
}
