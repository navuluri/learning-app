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

/**
 * Contains all the required CRUD operations that can be performed on Course details like
 * <ul>
 *     <li>
 *         Get all the course details
 *     </li>
 *     <li>
 *         Get course details for a particular course
 *     </li>
 *     <li>
 *         Create a new course
 *     </li>
 * </ul>
 * <p>
 *     It uses Spring framework's JdbcTemplate pattern for accessing the datasource (which in this case is a H2 database)
 * </p>
 *
 * @author Bhaskara Navuluri
 * @see org.springframework.stereotype.Repository
 */

@Repository
@Slf4j
@AllArgsConstructor
public class CourseRepository
{
    private final JdbcTemplate jdbcTemplate;


    /**
     * Queries the datasource and fetches Course details for a give course id
     *
     * @param courseId The course id for which the details have to be retrieved
     * @return course details for a given courseId
     * @see com.learning.app.models.Course
     * @see org.springframework.jdbc.core.JdbcTemplate
     */
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

    /**
     * Queries the datasource and fetches all the courses available in the database
     *
     * @return All the courses available in the database
     * @see com.learning.app.models.Course
     * @see org.springframework.jdbc.core.JdbcTemplate
     */
    public List<Course> getAllCourses()
    {
        return jdbcTemplate.query(QueryConstants.QUERY_GET_ALL_COURSES, (resultSet, i) -> new Course()
                .withId(resultSet.getString("ID"))
                .withName(resultSet.getString("NAME"))
                .withDescription(resultSet.getString("DESCRIPTION"))
                .withPricing(JsonUtility.jsonToJava(resultSet.getString("PRICING"), Pricing.class)));
    }

    /**
     * Persist course details into the database
     *
     * @param course The course details that are to be persisted into the database
     * @return course id. This id can be used to retrieve the course details
     * @see com.learning.app.models.Course
     * @see org.springframework.jdbc.core.JdbcTemplate
     */
    public String createCourse(Course course)
    {
        jdbcTemplate.update(QueryConstants.QUERY_INSERT_COURSE, course.getId(), course.getName(), course.getDescription(), JsonUtility.javaToJson(course.getPricing()));
        log.info("Record inserted successfully !");
        return course.getId();
    }
}
