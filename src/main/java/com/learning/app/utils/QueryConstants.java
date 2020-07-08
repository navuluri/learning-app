package com.learning.app.utils;

/**
 * Static constants to hold the database Queries.
 * <p>
 * Using JPA we can avoid writing these queries
 * </p>
 */
public class QueryConstants
{
    public static final String QUERY_GET_COURSE = "SELECT * FROM COURSE WHERE ID=?";
    public static final String QUERY_INSERT_COURSE = "INSERT INTO COURSE (ID, NAME, DESCRIPTION, PRICING) VALUES (?,?,?,?)";
    public static final String QUERY_GET_ALL_COURSES = "SELECT * FROM COURSE";
    public static final String QUERY_CREATE_COURSE_TABLE = "CREATE TABLE COURSE(ID VARCHAR NOT NULL PRIMARY KEY, NAME VARCHAR NOT NULL, DESCRIPTION VARCHAR NOT NULL, PRICING VARCHAR NOT NULL )";
    public static final String QUERY_DELETE_COURSE = "DELETE FROM COURSE WHERE ID = ?";
}
