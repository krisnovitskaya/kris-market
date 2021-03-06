package ru.krisnovitskaya.kris.market.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    /**
     * catch ResourceNotFoundException
     * @param e ResourceNotFoundException
     * @return ResponseEntity
     */
    @ExceptionHandler
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
        log.error(e.getMessage());
        MarketError err = new MarketError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    /**
     * catch WrongOrderStatusException
     * @param e WrongOrderStatusException
     * @return ResponseEntity
     */
    @ExceptionHandler
    public ResponseEntity<?> handleWrongStatusException(WrongOrderStatusException e) {
        log.error(e.getMessage());
        MarketError err = new MarketError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
