package com.devm8.stockalarm.exception;

/**
 * Custom exception for 404 NOT_FOUND status.
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(final String message) {
        super(message);
    }
}
