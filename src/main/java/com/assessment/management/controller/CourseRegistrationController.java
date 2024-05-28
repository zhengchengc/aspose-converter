package com.assessment.management.controller;

import com.assessment.management.entity.CourseRegistration;
import com.assessment.management.service.CourseRegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/courseRegistrations")
public class CourseRegistrationController {
    
    private CourseRegistrationService courseRegistrationService;

    public CourseRegistrationController(CourseRegistrationService courseRegistrationService) {
        this.courseRegistrationService = courseRegistrationService;
    }

    @GetMapping
    public List<CourseRegistration> getAllCourseRegistrations() {
        return courseRegistrationService.getAllCourseRegistrations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseRegistration> getCourseRegistrationById(@PathVariable Long id) {
        CourseRegistration courseRegistration = courseRegistrationService.getCourseRegistrationById(id);
        return courseRegistration != null ? ResponseEntity.ok(courseRegistration) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public CourseRegistration registerStudentForCourse(@RequestBody CourseRegistration courseRegistration) {
        return courseRegistrationService.registerStudentForCourse(courseRegistration);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourseRegistration(@PathVariable Long id) {
        courseRegistrationService.deleteCourseRegistration(id);
        return ResponseEntity.noContent().build();
    }
}
