package ru.krisnovitskaya.kris.market.exceptions;

public class WrongOrderStatusException extends RuntimeException{
    public WrongOrderStatusException(String message) {
        super(message);
    }
}
