package com.o4.mobility.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.o4.mobility.common.exceptions.ApplicationException;

import java.util.List;

/**
 * Utility for handling JSON operations using Jackson.
 * Provides methods to convert JSON strings into Java objects and lists of objects.
 *
 * @author M. Mazhar Hassan
 * @see ObjectMapper
 * @since 1.0
 */
public interface JsonUtils {
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Convert the object to Json String
     * @param object Object
     * @return String
     */
    static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new ApplicationException("Unable to convert object to Json", e);
        }
    }

    /**
     * Converts a JSON string to an object of the specified class.
     *
     * @param jsonString the JSON string to convert
     * @param clazz      the class of the object to return
     * @param <T>        the type of the object to return
     * @return the object of the specified class
     * @throws JsonProcessingException if there is an error processing the JSON
     */
    static <T> T toObject(String jsonString, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(jsonString, clazz);
    }

    /**
     * Converts a JSON string to a list of objects of the specified class.
     *
     * @param jsonString the JSON string to convert
     * @param clazz      the class of the objects in the list
     * @param <T>        the type of the objects in the list
     * @return the list of objects of the specified class
     * @throws JsonProcessingException if there is an error processing the JSON
     */
    static <T> List<T> toList(String jsonString, Class<T> clazz) throws JsonProcessingException {
        // Use TypeReference to specify the type of list
        return objectMapper.readValue(jsonString, new TypeReference<List<T>>() {
        });
    }
}
