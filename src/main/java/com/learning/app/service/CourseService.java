package com.learning.app.service;

import com.learning.app.models.Course;
import com.learning.app.repository.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${pricing.strategies}")
    private List<String> pricingStrategies;

    private final CourseRepository courseRepository;


    /**
     * Gets course details for a given courseId
     * <p>
     * If the pricing strategies are empty, by default we inject the pricing strategies defined in configuration.yml file
     * </p>
     *
     * @param courseId The id for which the course details have to be fetched
     * @return A course object that contains all the course details
     */
    public Course getCourse(String courseId)
    {
        Course course = courseRepository.getCourse(courseId);
        if (course.getPricing().getStrategies() == null || course.getPricing().getStrategies().size() == 0)
        {
            log.info("The pricing strategies are null. So assigning default pricing strategies");
            course.getPricing().setStrategies(pricingStrategies);
        }
        return course;
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
