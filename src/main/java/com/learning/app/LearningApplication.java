package com.learning.app;

import com.learning.app.models.Course;
import com.learning.app.repository.DatabaseInitializerRepository;
import com.learning.app.utils.JsonUtility;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootApplication()
@Slf4j
public class LearningApplication implements CommandLineRunner
{
    @Autowired
    private DatabaseInitializerRepository databaseInitializerRepository;

    public static void main(String[] args)
    {
        SpringApplication.run(LearningApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        Resource resource = new ClassPathResource("courses.json");
        String json = FileUtils.readFileToString(resource.getFile(), StandardCharsets.UTF_8);
        List<Course> courseList = JsonUtility.jsonArrayToJava(json, Course.class);
        log.info("Initializing the DB - Start");
        databaseInitializerRepository.createSchema();
        courseList.forEach(course -> databaseInitializerRepository.initDb(course));
        log.info("Initializing the DB - End");

    }
}
