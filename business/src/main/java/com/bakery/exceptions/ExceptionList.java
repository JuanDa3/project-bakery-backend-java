package com.bakery.exceptions;

public class ExceptionList extends RuntimeException{

    private static final long SerialVersionUID = 1L;

    public ExceptionList(String message) {
        super(message);
    }
}
