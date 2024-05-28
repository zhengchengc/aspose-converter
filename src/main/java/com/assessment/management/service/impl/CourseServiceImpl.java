package com.assessment.management.service.impl;

import com.assessment.management.entity.Course;
import com.assessment.management.repository.CourseRepository;
import com.assessment.management.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public Course createCourse(Course course) {
        try {
            Course newCourse = courseRepository.save(course);
            log.info("Course created: " + newCourse);
            return newCourse;
        } catch (Exception e) {
            log.error("Create course {} failed", course);
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course updateCourse(Long id, Course updatedCourse) {
        Course existingCourse = courseRepository.findById(id).orElse(null);
        if (!ObjectUtils.isEmpty(existingCourse)) {
            existingCourse.setCourseName(updatedCourse.getCourseName());
            existingCourse.setCourseCode(updatedCourse.getCourseCode());
            existingCourse.setCourseDescription(updatedCourse.getCourseDescription());
            return courseRepository.save(existingCourse);
        }
        return null;
    }
}
