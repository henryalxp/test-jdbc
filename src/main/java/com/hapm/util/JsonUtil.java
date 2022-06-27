package com.hapm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hapm.dto.EmployeeDto;

public class JsonUtil {

    private static JsonUtil instance;
    private ObjectMapper mapper = new ObjectMapper();

    private JsonUtil() {

    }

    public static JsonUtil getInstance() {
        return instance != null ? instance : new JsonUtil();
    }

    public String mapToString(Object input) {
        try {
            return mapper.writeValueAsString(input);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public EmployeeDto mapToDto(String input) {
        try {
            return mapper.readValue(input, EmployeeDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
