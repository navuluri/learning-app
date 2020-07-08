package com.learning.app.utils;

public class QueryConstants
{
    public static final String QUERY_GET_COURSE = "SELECT * FROM COURSE WHERE ID=?";
    public static final String QUERY_INSERT_COURSE = "INSERT INTO COURSE (ID, NAME, DESCRIPTION, PRICING) VALUES (?,?,?,?)";
    public static final String QUERY_GET_ALL_COURSES = "SELECT * FROM COURSE";
    public static final String QUERY_CREATE_COURSE_TABLE = "CREATE TABLE COURSE(ID VARCHAR NOT NULL PRIMARY KEY, NAME VARCHAR NOT NULL, DESCRIPTION VARCHAR NOT NULL, PRICING VARCHAR NOT NULL )";
}
