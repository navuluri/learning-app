package com.learning.app.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a Utility class that performs the below JSON operations
 * <br>
 * <ul>
 * <li>Converting JSON to a POJO (Deserialization)</li>
 * <li>Converting POJO to JSON (Serialization)</li>
 * <li>Converting a JSON array to List of POJOs</li>
 * </ul>
 * <p>
 * The class uses <b>Jackson</b> libraries for Serialization and Deserialization of JSON
 * </p>
 *
 * <p>
 * The class contains a set of Static methods and hence new operator is not allowed on this class. The same is suggested by Sonar : java:S1118
 * </p>
 */

@Slf4j
public class JsonUtility
{
    private static final ObjectMapper mapper;

    /**
     * No Access Constructor. Use the static methods
     */
    private JsonUtility()
    {
        // No init please
    }

    /**
     * Setting global properties on Jackson ObjectMapper
     * */
    static
    {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    }


    public static <T> List<T> jsonArrayToJava(String json, Class<T> type)
    {
        try
        {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, type));

        }
        catch (Exception ex)
        {
            log.error("There is an error while converting  JSON Array to JAVA ", ex);
        }
        return new ArrayList<>();
    }

    public static JsonNode getJsonNode(String json, String key)
    {
        try
        {
            return mapper.readTree(json).get(key);
        }
        catch (Exception ex)
        {
            log.error("There is an error while getting JsonNode ", ex);
            return null;
        }

    }

    public static String javaToJson(Object o)
    {
        try
        {
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            return mapper.writeValueAsString(o);
        }
        catch (Exception ex)
        {
            log.error("There is an error while converting  JAVA to JSON ", ex);
        }
        return o.toString();
    }

    public static <T> T jsonToJava(String json, Class<T> type)
    {
        try
        {
            return mapper.readValue(json, type);
        }
        catch (Exception ex)
        {
            log.error("There is an error while converting  JSON to JAVA ", ex);
        }
        return null;
    }


}
