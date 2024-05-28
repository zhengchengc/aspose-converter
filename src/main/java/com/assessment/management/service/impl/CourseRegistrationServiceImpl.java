package com.assessment.management.service.impl;

import com.assessment.management.entity.CourseRegistration;
import com.assessment.management.repository.CourseRegistrationRepository;
import com.assessment.management.service.CourseRegistrationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseRegistrationServiceImpl implements CourseRegistrationService {

    private CourseRegistrationRepository courseRegistrationRepository;

    public CourseRegistrationServiceImpl(CourseRegistrationRepository courseRegistrationRepository) {
        this.courseRegistrationRepository = courseRegistrationRepository;
    }

    public List<CourseRegistration> getAllCourseRegistrations() {
        return courseRegistrationRepository.findAll();
    }

    public CourseRegistration getCourseRegistrationById(Long id) {
        return courseRegistrationRepository.findById(id).orElse(null);
    }

    public CourseRegistration registerStudentForCourse(CourseRegistration courseRegistration) {
        courseRegistration.setRegistrationDate(LocalDateTime.now());
        return courseRegistrationRepository.save(courseRegistration);
    }

    public void deleteCourseRegistration(Long id) {
        courseRegistrationRepository.deleteById(id);
    }
}
