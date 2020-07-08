package com.learning.app.exceptions;

import com.learning.app.models.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CourseExceptionAdvice extends ResponseEntityExceptionHandler
{
    @ExceptionHandler({CourseNotFoundException.class})
    protected ResponseEntity<Result> handleCourseNotFound(CourseNotFoundException ex)
    {
        Result result = new Result();
        result.setMessage(ex.getMessage());
        result.setCode(ex.getCode());
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }
}
