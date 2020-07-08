package com.learning.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Result
{
    @With
    private int code;
    @With
    private String message;
}
