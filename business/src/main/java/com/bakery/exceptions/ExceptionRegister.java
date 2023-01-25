package com.bakery.exceptions;

public class ExceptionRegister extends RuntimeException{

    private static final long SerialVersionUID = 1L;

    public ExceptionRegister(String message) {
        super(message);
    }
}
