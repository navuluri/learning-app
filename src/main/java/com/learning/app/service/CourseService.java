package com.learning.app.service;

import com.learning.app.models.Course;
import com.learning.app.repository.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CourseService
{
    private final CourseRepository courseRepository;

    public Course getCourse(String courseId)
    {
        return courseRepository.getCourse(courseId);
    }

    public List<Course> getAllCourses()
    {
        return courseRepository.getAllCourses();
    }
}
