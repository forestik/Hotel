package com.hotels.exceptions;

/**
 * Exception that we get when user whan passwords don't matches.
 *
 */
public class PasswordsDoNotMatchesException extends RuntimeException {
    /**
     * Constructor for PasswordsDoNotMatchesException.
     *
     * @param message - giving message.
     */
    public PasswordsDoNotMatchesException(String message) {
        super(message);
    }
}
