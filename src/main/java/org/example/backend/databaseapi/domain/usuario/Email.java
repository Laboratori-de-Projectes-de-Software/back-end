package org.example.backend.databaseapi.domain.usuario;

import org.example.backend.databaseapi.application.exception.ValidationException;

import java.util.regex.Pattern;

public record Email(String value) {

    private static final Pattern EMAIL_REGEX = Pattern.compile(
            "^.+@.+\\..+$"
    );

    public Email{
        if (value == null || !EMAIL_REGEX.matcher(value).matches()) {
            throw new ValidationException("Invalid email format: " + value);
        }
    }

    @JsonValue
    public String value() {
        return value;
    }
}
