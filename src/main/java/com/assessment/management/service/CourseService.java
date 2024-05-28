package com.assessment.management.service;

import com.assessment.management.entity.Course;
import com.assessment.management.entity.Student;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    Course getCourseById(Long id);
    Course createCourse(Course course);
    void deleteCourse(Long id);
    Course updateCourse(Long id, Course course);
}
