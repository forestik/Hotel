package com.hotels.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Exception that we get when user trying to sign-in to account that is
 * deactivated.
 *
 */
public class UserDeactivatedException extends AuthenticationException {
    /**
     * Constructor.
     */
    public UserDeactivatedException(String message) {
        super(message);
    }
}
