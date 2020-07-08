package com.learning.app.repository;

import com.learning.app.models.Course;
import com.learning.app.models.Pricing;
import com.learning.app.utils.JsonUtility;
import com.learning.app.utils.QueryConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Slf4j
@AllArgsConstructor
public class CourseRepository
{
    private final JdbcTemplate jdbcTemplate;

    public Course getCourse(String courseId)
    {
        try
        {
            return jdbcTemplate.queryForObject(
                    QueryConstants.QUERY_GET_COURSE,
                    new Object[]{courseId},
                    (resultSet, i) -> new Course()
                            .withId(resultSet.getString("ID"))
                            .withName(resultSet.getString("NAME"))
                            .withDescription(resultSet.getString("DESCRIPTION"))
                            .withPricing(JsonUtility.jsonToJava(resultSet.getString("PRICING"), Pricing.class))
            );
        }
        catch (EmptyResultDataAccessException ex)
        {
            log.warn("No course found with id {}", courseId);
            return null;
        }
    }

    public List<Course> getAllCourses()
    {
        return jdbcTemplate.query(QueryConstants.QUERY_GET_ALL_COURSES, (resultSet, i) -> new Course()
                .withId(resultSet.getString("ID"))
                .withName(resultSet.getString("NAME"))
                .withDescription(resultSet.getString("DESCRIPTION"))
                .withPricing(JsonUtility.jsonToJava(resultSet.getString("PRICING"), Pricing.class)));
    }

    public String createCourse(Course course)
    {
        if (course.getId() == null)
        {
            course.setId(UUID.randomUUID().toString());
        }
        jdbcTemplate.update(QueryConstants.QUERY_INSERT_COURSE, course.getId(), course.getName(), course.getDescription(), JsonUtility.javaToJson(course.getPricing()));
        log.info("Record inserted successfully !");
        return course.getId();
    }
}
