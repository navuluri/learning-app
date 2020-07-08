package com.learning.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CourseExceptionAdvice extends ResponseEntityExceptionHandler
{
    @ExceptionHandler({CourseNotFoundException.class})
    protected ResponseEntity<Error> handleCourseNotFound(CourseNotFoundException ex)
    {
        Error error = new Error();
        error.setMessage(ex.getMessage());
        error.setCode(ex.getCode());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
