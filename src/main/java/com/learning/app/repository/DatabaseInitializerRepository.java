package com.learning.app.repository;


import com.learning.app.models.Course;
import com.learning.app.utils.JsonUtility;
import com.learning.app.utils.QueryConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class DatabaseInitializerRepository
{
    private final JdbcTemplate jdbcTemplate;

    public DatabaseInitializerRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void initDb(Course course)
    {
        jdbcTemplate.update(QueryConstants.QUERY_INSERT_COURSE, course.getId(), course.getName(), course.getDescription(), JsonUtility.javaToJson(course.getPricing()));
    }

    public void createSchema()
    {
        log.info("Creating schema ...");
        jdbcTemplate.execute(QueryConstants.QUERY_CREATE_COURSE_TABLE);
        log.info("Schema created ");
    }


}
