package com.hotels.service;

/**
 * Provides the interface to manage sending emails to {@code User}.
 */
public interface EmailService {

    /**
     * Method for sending verification email to user
     *
     * @param userId    user id.
     * @param userName  name current user.
     * @param userEmail email current user.
     * @param token     verify token current user.
     */
    void sendVerificationEmail(Long userId, String userName, String userEmail, String token);

}
