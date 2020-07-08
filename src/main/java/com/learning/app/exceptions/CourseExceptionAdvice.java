package com.learning.app.exceptions;


import com.learning.app.models.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This is a common place for handling Exceptions.
 * This is specifically useful to return exception code and message in a RESTful way instead of returning a stacktrace or a HTML content
 *
 * @author Bhaskara Navuluri
 * @see org.springframework.web.bind.annotation.RestControllerAdvice
 * @since 1.0
 */

@RestControllerAdvice
public class CourseExceptionAdvice extends ResponseEntityExceptionHandler
{
    /**
     * @param ex The exception object this is handed-over to this advice by the Spring Framework. In this case, it contains Status Code and the Message
     * @return A response entity that has a Result with HTTP status  code and a appropriate message that caused this exception
     * @see com.learning.app.models.Result
     * @see org.springframework.web.bind.annotation.ExceptionHandler
     * @see org.springframework.http.ResponseEntity
     */
    @ExceptionHandler({CourseNotFoundException.class})
    protected ResponseEntity<Result> handleCourseNotFound(CourseNotFoundException ex)
    {
        Result result = new Result();
        result.setMessage(ex.getMessage());
        result.setCode(ex.getCode());
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }
}
