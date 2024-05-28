package com.assessment.management.service;

import com.assessment.management.entity.CourseRegistration;

import java.util.List;

public interface CourseRegistrationService {
    List<CourseRegistration> getAllCourseRegistrations();
    CourseRegistration getCourseRegistrationById(Long id);
    CourseRegistration registerStudentForCourse(CourseRegistration courseRegistration);
    void deleteCourseRegistration(Long id);
}
