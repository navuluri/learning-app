package com.learning.app.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CourseNotFoundException extends RuntimeException
{
    private final int code;
    private final String message;
}
