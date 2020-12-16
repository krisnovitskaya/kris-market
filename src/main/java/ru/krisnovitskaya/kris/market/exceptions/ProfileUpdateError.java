package ru.krisnovitskaya.kris.market.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ProfileUpdateError {
    private int status;
    private String message;
    private Date timestamp;

    public ProfileUpdateError(List<ObjectError> errors) {
        this(makeMessage(errors));
    }

    public ProfileUpdateError(String message) {
        this.status = HttpStatus.BAD_REQUEST.value();
         this.message = message;
        this.timestamp = new Date();
    }


    private static String makeMessage(List<ObjectError> errors){
        StringBuilder sb = new StringBuilder();
        for (ObjectError error : errors) {
            sb.append(error.getDefaultMessage());
            sb.append("; ");
        }
        return sb.toString().trim();
    }
}
