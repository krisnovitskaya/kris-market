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
    private String message;
    private Date timestamp;

    public RegistrationError(List<ObjectError> errors) {
        this.status = HttpStatus.BAD_REQUEST.value();
        this.timestamp = new Date();
        StringBuilder sb = new StringBuilder();
        for (ObjectError error : errors) {
            sb.append(error.getDefaultMessage());
            sb.append("; ");
        }
        this.message = sb.toString().trim();
        System.out.println(this.message);
    }

    public RegistrationError(String message) {
        this.status = HttpStatus.BAD_REQUEST.value();
        this.message = message;
        this.timestamp = new Date();
    }
}
