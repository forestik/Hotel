package com.hotels.service;


import com.hotels.dto.SuccessSignInDto;

/**
 * Provides the google social logic.
 *
 */

public interface GoogleSecurityService {
    /**
     * Method that allow you authenticate with google idToken.
     *
     * @param idToken {@link String} - google id token.
     * @return {@link SuccessSignInDto} if token valid
     */
    SuccessSignInDto authenticate(String idToken);
}
