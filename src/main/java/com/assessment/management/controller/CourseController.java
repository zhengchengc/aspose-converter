package com.assessment.management.controller;

import com.assessment.management.entity.Course;
import com.assessment.management.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@Slf4j
public class CourseController {
    
    CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        List<Course> courseList = courseService.getAllCourses();
        log.info("getAllCourses: {}", courseList);
        return courseList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        log.info("Searching Course with ID: {}", id);
        Course course = courseService.getCourseById(id);
        if (ObjectUtils.isEmpty(course)) {
            log.warn("Update course {} failed", id);
            return ResponseEntity.notFound().build();
        } else {
            log.info("Update course {} successful", id);
            return ResponseEntity.ok(course);
        }
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        try {
            log.info("Creating course {}", course);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(courseService.createCourse(course));
        } catch (Exception e) {
            log.warn("Create course {} failed", course);
            log.warn(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
        log.info("Updating Course with ID: {} {}", id, updatedCourse);
        Course course = courseService.updateCourse(id, updatedCourse);
        if (ObjectUtils.isEmpty(course)) {
            log.warn("Update course {} failed", updatedCourse);
            return ResponseEntity.notFound().build();
        } else {
            log.info("Update course {} successful", updatedCourse);
            return ResponseEntity.ok(course);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        log.info("Deleting Course with ID: {}", id);
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
