package jaumesitos.backend.demo.infrastructure.db.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;

@Converter
public class IntegerArrayToJsonConverter implements AttributeConverter<Integer[], String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Integer[] attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer[] convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Integer[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
