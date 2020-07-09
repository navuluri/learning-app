package com.learning.app;

import com.learning.app.models.Course;
import com.learning.app.repository.DatabaseInitializerRepository;
import com.learning.app.utils.JsonUtility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.nio.charset.StandardCharsets;
import java.util.List;


/**
 * The starting point of the application. The Spring Boot framework calls the main method which inits the application
 * <p>
 * As part of the application startup we are bootstrapping some test data that is scraped from Udemy
 * </p>
 *
 * @author Bhaskara Navuluri
 * @since 1.0
 */

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class LearningApplication implements CommandLineRunner
{
    private final DatabaseInitializerRepository databaseInitializerRepository;
    private final ResourceLoader resourceLoader;


    /**
     * The start-point of the application. Spring Boot calls the  method to startup the application
     *
     * @param args Contains all the command line arguments (if any)
     */
    public static void main(String[] args)
    {
        SpringApplication.run(LearningApplication.class, args);
    }

    /**
     * As part of the data init, we call a CommandLineRunner's run
     * method that gets invoked by Spring Boot while the application is starting up
     * <p>
     * The method reads the JSON details from the course.json (that is part of the application).
     * </p>
     * <p>The course.json contains all the course details that are scraped (using Selenium) from Udemy. The json file will be present in the claspath.</p>
     * <p>The method is called once in its lifetime - during the application startup</p>
     *
     * @param args The command line arguments (if any) can be passed to the application
     * @throws Exception Throws an Exception if the data init is failed and this enables us to fail-fast and the application fails to startup
     */
    @Override
    public void run(String... args) throws Exception
    {
        Resource resource = resourceLoader.getResource("classpath:courses.json");
        String json = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
        List<Course> courseList = JsonUtility.jsonArrayToJava(json, Course.class);
        log.info("Initializing the DB - Start");
        databaseInitializerRepository.createSchema();
        courseList.forEach(databaseInitializerRepository::initDb);
        log.info("Initializing the DB - End");

    }
}
