package com.anderson.address_api.shared.exceptions;

public class AlreadyRegisteredException extends RuntimeException {
    public AlreadyRegisteredException(String message) {
        super(message);
    }
}
