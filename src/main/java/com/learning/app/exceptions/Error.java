package com.learning.app.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Error
{
    private int code;
    private String message;
}
