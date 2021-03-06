package com.learning.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

/**
 * The value object that holds the entire course details
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course
{
    @With
    private String id;
    @With
    private String name;
    @With
    private String description;
    @With
    private Pricing pricing;

}
