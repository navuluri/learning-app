
package com.learning.app.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The holder for the Course not found exception
 * This is thrown when the user queries for a course that is not yet available
 *
 * @author Bhaskara Navuluri
 * @since 1.0
 */

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CourseNotFoundException extends RuntimeException
{
    private final int code;
    private final String message;
}
