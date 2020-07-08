package com.learning.app.repository;


import com.learning.app.models.Course;
import com.learning.app.utils.JsonUtility;
import com.learning.app.utils.QueryConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * The data initializer. As part of this prototype, we need to provision some data into the datasource.
 * This class acts as the data init'er
 *
 * @author Bhaskara Navuluri
 * @since 1.0
 */
@Repository
@Slf4j
public class DatabaseInitializerRepository
{
    private final JdbcTemplate jdbcTemplate;

    /**
     * Injects the JdbcTemplate instance into this class
     *
     * @param jdbcTemplate The template pattern that will be used to query the data from the data stoer
     */
    public DatabaseInitializerRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * @param course Persists the course object into the datastore. This gets invoked during application startup and inits the datastore with course details
     */
    public void initDb(Course course)
    {
        jdbcTemplate.update(QueryConstants.QUERY_INSERT_COURSE, course.getId(), course.getName(), course.getDescription(), JsonUtility.javaToJson(course.getPricing()));
    }

    /**
     * Creates a new schema in the data store. This gets invoked during the application startup
     */
    public void createSchema()
    {
        log.info("Creating schema ...");
        jdbcTemplate.execute(QueryConstants.QUERY_CREATE_COURSE_TABLE);
        log.info("Schema created ");
    }


}
