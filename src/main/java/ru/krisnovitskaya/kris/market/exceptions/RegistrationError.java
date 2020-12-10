package ru.krisnovitskaya.kris.market.exceptions;


import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class RegistrationError {
    private int status;
    private List<String> messages;
    private Date timestamp;

    public RegistrationError(List<ObjectError> errors) {
        this.status = HttpStatus.BAD_REQUEST.value();
        this.messages = errors.stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        this.timestamp = new Date();
    }

    public RegistrationError(String message) {
        this.status = HttpStatus.BAD_REQUEST.value();
        this.messages = new ArrayList<>(Arrays.asList(message));
        this.timestamp = new Date();
    }
}
