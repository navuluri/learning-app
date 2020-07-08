package com.learning.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pricing
{
    @With
    private double cost;
    @With
    private double discount;
    @With
    private double gst;
    @With
    private double cess;
    @With
    private List<String> strategies;


}
