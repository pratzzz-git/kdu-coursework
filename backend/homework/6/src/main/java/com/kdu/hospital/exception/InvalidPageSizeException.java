package com.kdu.hospital.exception;

public class InvalidPageSizeException extends RuntimeException {
    public InvalidPageSizeException(String message) {
        super(message);
    }
}
