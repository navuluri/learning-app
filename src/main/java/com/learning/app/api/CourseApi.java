package com.learning.app.api;

import com.learning.app.exceptions.CourseNotFoundException;
import com.learning.app.models.Course;
import com.learning.app.models.Result;
import com.learning.app.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * The base class for all the Course APIs. All the APIs adhere to REST and HTTP principles.
 * <ul>
 * <li><strong>POST</strong> - Create a new Course</li>
 * <li><strong>PUT</strong> - Update the Course details</li>
 * <li><strong>GET</strong> - Get all the courses or a single course based on the course ID</li>
 * <li><strong>DELETE</strong> - Delete a particular course based on the course ID</li>
 * </ul>
 * <p>
 * The class uses Project Lombok annotations to reduce the Boilerplate code.
 *
 * @author Bhaskara Navuluri
 * @see <a href="https://projectlombok.org/">Project Lombok</a>
 * </p>
 * @since 1.0
 */


@RestController
@RequestMapping("/api/v1/courses")
@Slf4j
@AllArgsConstructor
@Api(tags = {"Course API"})
public class CourseApi
{
    private final CourseService courseService;


    /**
     * The API gets the Course details for a given course ID. The API responds to  HTTP GET method and returns a JSON object
     *
     * @param courseId The course id for which the details have to be fetched
     * @return Returns the course details in  a JSON format. <p><strong>Example Output:</strong></p>
     * <pre> {@code}
     * {
     *   "id": "100",
     *   "name": "Build Your Own Super Computer with Raspberry Pis",
     *   "description": "Create a computer cluster using Raspberry Pi boards - everything from hardware, software, design and networks",
     *   "pricing": {
     *     "cost": 4726.05,
     *     "discount": 15.15,
     *     "gst": 23.79,
     *     "cess": 1.57,
     *     "strategies": [
     *       "Free",
     *       "One-Time",
     *       "Subscription"
     *     ]
     *   }
     * }
     * </pre>
     * @see com.learning.app.models.Course
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Gets the course details for a given course ID", produces = "application/json")
    @ApiResponses(value = {

            @ApiResponse(code = 200, message = "Returns a course JSON response for a given id"),
            @ApiResponse(code = 401, message = "You are not authorized to view the course"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The course you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "The course could not be fetched. There is an internal server error ")

    })
    public Course getCourse(@PathVariable("id") String courseId)
    {
        log.info("Getting course information for the Course Id : {}", courseId);
        return Optional
                .ofNullable(courseService.getCourse(courseId))
                .orElseThrow(() -> new CourseNotFoundException(HttpStatus.NOT_FOUND.value(), "There is no course with the id : " + courseId));
    }

    /**
     * The API gets details of all the courses in the system. The API responds to HTTP GET method and returns a JSON object.
     * Basically this API should take the pagination parameters like count, min, and max records. For simplicity, omitted the implementation of pagination
     *
     * @return Returns all the course details in  a JSON format. <p><strong>Example Output:</strong></p>
     * <pre> {@code}
     *  [{
     *  	"id": "100",
     *  	"name": "Build Your Own Super Computer with Raspberry Pis",
     *  	"description": "Create a computer cluster using Raspberry Pi boards - everything from hardware, software, design and networks",
     *  	"pricing": {
     *  		"cost": 4726.05,
     *  		"discount": 15.15,
     *  		"gst": 23.79,
     *  		"cess": 1.57,
     *  		"strategies": [
     *  			"Free",
     *  			"One-Time",
     *  			"Subscription"
     *  		]
     *        }
     *  }, {
     *  	"id": "1",
     *  	"name": "Code Your First Game: Arcade Classic in JavaScript on Canvas",
     *  	"description": "Program a complete game today. No special software or install required. All you need is a text editor and a web browser.",
     *  	"pricing": {
     *  		"cost": 3302.91,
     *  		"discount": 39.61,
     *  		"gst": 12.55,
     *  		"cess": 14.18,
     *  		"strategies": []
     *    }
     *  }]
     * </pre>
     * @see com.learning.app.models.Course
     */
    @GetMapping("")
    @ApiOperation(value = "Gets all the courses details in the system", produces = "application/json")
    @ApiResponses(value = {

            @ApiResponse(code = 200, message = "Returns list of all the courses available"),
            @ApiResponse(code = 401, message = "You are not authorized to view the course"),
            @ApiResponse(code = 403, message = "Accessing the course you were trying to reach is forbidden"),
            @ApiResponse(code = 500, message = "The courses could not be fetched. There is an internal server error ")
    })
    public List<Course> getAllCourses()
    {
        log.info("Getting all the courses");
        return courseService.getAllCourses();
    }

    /**
     * The API creates a new course
     *
     * @param course The course that has to be created
     * @return Returns the course id. <p><strong>Example Output:</strong></p>
     *
     * <pre> {@code}
     * HTTP Status Line: 201 Created
     * {
     *   "code": 201,
     *   "message": "Course created successfully. Course id is : a4e02352-0d34-4462-a589-7ea4ac0bb821"
     * }
     * </pre>
     * Request Payload
     * <pre> {@code}
     *
     * {
     * 	"name": "Code Your First Game: Arcade Classic in JavaScript on Canvas",
     * 	"description": "Program a complete game today. No special software or install required. All you need is a text editor and a web browser.",
     * 	"pricing": {
     * 		"cost": 3302.91,
     * 		"discount": 39.61,
     * 		"gst": 12.55,
     * 		"cess": 14.18,
     * 		"strategies": ["Free"]
     *        }
     * }
     *
     * </pre>
     * @see com.learning.app.models.Course
     * @see com.learning.app.models.Result
     */
    @PostMapping("")
    @ApiOperation(value = "Create a new course", produces = "application/json", consumes = "application/json")
    @ApiResponses(value = {

            @ApiResponse(code = 201, message = "A course with given details is created"),
            @ApiResponse(code = 401, message = "You are not authorized to create the course"),
            @ApiResponse(code = 500, message = "The course could not be created. There is an internal server error ")

    })
    public ResponseEntity<Result> createCourse(@RequestBody Course course)
    {
        log.info("Provisioning a new course");
        String id = courseService.createCourse(course);
        return new ResponseEntity<>(new Result().withCode(HttpStatus.CREATED.value()).withMessage("Course created successfully. Course id is : " + id), HttpStatus.CREATED);
    }


    /**
     * Deletes the course with the given Id. Does not return anything. Empty response with a HTTP Status 204 - No Content
     *
     * @param courseId The id for which the details have to be deleted
     * @return Void. Returns an empty response
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a course with the given courseId")
    @ApiResponses(value = {

            @ApiResponse(code = 204, message = "The course with the given id is deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to delete the course"),
            @ApiResponse(code = 403, message = "Accessing the course you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The course you were trying to delete is not found"),
            @ApiResponse(code = 500, message = "The course could not be deleted. There is an internal server error ")

    })
    public ResponseEntity<Void> deleteCourse(@PathVariable("id") String courseId)
    {
        log.info("Deleting course with id {}", courseId);
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
