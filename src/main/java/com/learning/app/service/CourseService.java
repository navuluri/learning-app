package com.learning.app.service;

import com.learning.app.models.Course;
import com.learning.app.repository.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Contains all the business logic - like performing business checks, calculations etc that are critical for the business
 *
 * @author Bhaskara Navuluri
 * @since 1.0
 */

@Service
@Slf4j
@AllArgsConstructor
public class CourseService
{
    private final CourseRepository courseRepository;


    /**
     * Gets course details for a given courseId
     *
     * @param courseId The id for which the course details have to be fetched
     * @return A course object that contains all the course details
     */
    public Course getCourse(String courseId)
    {
        return courseRepository.getCourse(courseId);
    }

    /**
     * Gets all the courses that are available
     *
     * @return Returns a List of Course objects that are available
     */
    public List<Course> getAllCourses()
    {
        return courseRepository.getAllCourses();
    }

    /**
     * Provision a new course with the given data
     *
     * @param course The course details that are to be persisted in the datastore
     * @return A course id that can be used to fetch the course details or delete the course based in the id
     */
    public String createCourse(Course course)
    {
        if (course.getId() == null)
        {
            course.setId(UUID.randomUUID().toString());
        }
        return courseRepository.createCourse(course);
    }

    /**
     * Delete a  course with the given courseId
     *
     * @param courseId Delete the course details with the given courseId
     */
    public void deleteCourse(String courseId)
    {
        courseRepository.deleteCourse(courseId);
    }
}
