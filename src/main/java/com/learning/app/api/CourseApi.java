package com.learning.app.api;

import com.learning.app.exceptions.CourseNotFoundException;
import com.learning.app.models.Course;
import com.learning.app.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/courses")
@Slf4j
@AllArgsConstructor
public class CourseApi
{
    private final CourseService courseService;

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable("id") String courseId)
    {
        log.info("Getting course information for the Course Id : {}", courseId);
        return Optional
                .ofNullable(courseService.getCourse(courseId))
                .orElseThrow(() -> new CourseNotFoundException(HttpStatus.NOT_FOUND.value(), "There is no course with the id : " + courseId));
    }

    @GetMapping("")
    public List<Course> getAllCourses()
    {
        log.info("Getting all the courses");
        return courseService.getAllCourses();
    }
}
