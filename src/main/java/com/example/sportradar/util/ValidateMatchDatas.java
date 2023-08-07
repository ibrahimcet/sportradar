package com.example.sportradar.util;


import com.example.sportradar.entity.Match;
import com.example.sportradar.exception.InvalidRequestException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ValidateMatchDatas {

    private static ObjectMapper objectMapper = null;

    @Autowired
    ObjectMapper commonObjectMapper;

    @PostConstruct
    public void initObjectMapper(){
        objectMapper = commonObjectMapper;
    }

    private static final Set<String> NON_PATCHABLE_ATTRIBUTES = new HashSet<>(
            Arrays.asList("id", "homeTeam", "awayTeam"));

    public static <T> T deserializeFromJson(String jsonStr, Class<T> clazz) throws IOException {
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (IOException exception) {
            throw exception;
        }
    }

    public void validate(String input) throws Exception {
        try {
            Match inputEntity =deserializeFromJson(input, Match.class);
            checkForNonPatchableAttributes(inputEntity);
        }catch (IOException e){
            throw e;
        }
    }

    public static List<Field> getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<>();
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
        }
        return fields;
    }

    public void checkForNonPatchableAttributes(Object o) throws Exception {

        Class objectClass = o.getClass();
        List<Field> patchFields = getAllFields(objectClass).stream()
                .filter(f -> NON_PATCHABLE_ATTRIBUTES.contains(f.getName()))
                .collect(Collectors.toList());

        for (Field field : patchFields) {
            field.setAccessible(true);

            Object value;
            try {
                value = field.get(o);
            } catch (IllegalAccessException e) {
                throw new Exception();
            }

            if (value != null) {
                throw new InvalidRequestException(("Request contains non patchable attributes => " + field.getName() + ": " + value));
            }
        }
    }
}
