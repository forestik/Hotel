package com.hotels.service;

import com.hotels.dto.AccessRefreshTokensDto;
import com.hotels.dto.OwnSignInDto;
import com.hotels.dto.OwnSignUpDto;
import com.hotels.dto.SuccessSignInDto;
import com.hotels.dto.SuccessSignUpDto;
import com.hotels.dto.UpdatePasswordDto;

/**
 * Provides the interface to manage {@link OwnSecurityService} entity.
 *
 */
public interface OwnSecurityService {
    /**
     * Method that allow you sign-up user.
     *
     * @param dto a value of {@link OwnSignUpDto}
     * @return {@link SuccessSignUpDto}
     */
    SuccessSignUpDto signUp(OwnSignUpDto dto);

    /**
     * Method that allow you sign-in user.
     *
     * @param dto a value of {@link OwnSignInDto}
     * @return {@link SuccessSignInDto}
     */
    SuccessSignInDto signIn(OwnSignInDto dto);

    /**
     * Method that update your access token by refresh token.
     *
     * @param refreshToken a value of {@link String}
     * @return {@link AccessRefreshTokensDto} this is DTO with new access token
     */
    AccessRefreshTokensDto updateAccessTokens(String refreshToken);

    /**
     * Method for updating password.
     *
     * @param pass {@link String}
     * @param id   {@link Long}
     */
    void updatePassword(String pass, Long id);

    /**
     * Method for updating current password.
     *
     * @param updatePasswordDto {@link UpdatePasswordDto}
     * @param email             {@link String} - user email.
     */
    void updateCurrentPassword(UpdatePasswordDto updatePasswordDto, String email);
}
