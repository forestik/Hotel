package com.hotels.service;

import com.hotels.dto.EmailDto;

/**
 * Provides the interface to manage sending emails to {@code User}.
 */
public interface EmailService {

    /**
     * Method for sending verification email to user.
     *
     * @param emailDto {@link EmailDto}
     */
    void sendVerificationEmail(EmailDto emailDto);

    /**
     * Method for sending email to user.
     *
     * @param emailDto {@link EmailDto}
     */
    void sendInfoEmail(EmailDto emailDto);
}
