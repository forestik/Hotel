package com.hotels.service.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.hotels.constant.ErrorMessage;
import com.hotels.dto.OwnSignUpDto;
import com.hotels.dto.SuccessSignInDto;
import com.hotels.entity.User;
import com.hotels.jwt.JwtTool;
import com.hotels.service.GoogleSecurityService;
import com.hotels.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static com.hotels.constant.AppConstant.FAMILY_NAME;
import static com.hotels.constant.AppConstant.GIVEN_NAME;
import static com.hotels.constant.AppConstant.GOOGLE_PICTURE;

/**
 * {@inheritDoc}
 */
@Slf4j
@Service
public class GoogleSecurityServiceImpl extends AuthService implements GoogleSecurityService {
    private final UserService userService;
    private final GoogleIdTokenVerifier googleIdTokenVerifier;

    /**
     * Constructor.
     *
     * @param jwtTool               {@link JwtTool}
     * @param userService           {@link UserService}
     * @param googleIdTokenVerifier {@link GoogleIdTokenVerifier}
     */
    public GoogleSecurityServiceImpl(JwtTool jwtTool, UserService userService,
        GoogleIdTokenVerifier googleIdTokenVerifier) {
        super(jwtTool);
        this.userService = userService;
        this.googleIdTokenVerifier = googleIdTokenVerifier;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public SuccessSignInDto authenticate(String idToken) {
        try {
            GoogleIdToken googleIdToken = googleIdTokenVerifier.verify(idToken);
            if (googleIdToken != null) {
                GoogleIdToken.Payload payload = googleIdToken.getPayload();
                String email = payload.getEmail();
                String firstName = (String) payload.get(GIVEN_NAME);
                String lastName = (String) payload.get(FAMILY_NAME);
                User user = userService.findByEmail(email);
                if (user == null) {
                    log.info(ErrorMessage.USER_NOT_FOUND_BY_EMAIL + email);
                    var ownSignUpDto = new OwnSignUpDto(firstName, lastName, email, null);
                    user = createNewRegisteredUser(ownSignUpDto, (String) payload.get(GOOGLE_PICTURE));
                    userService.save(user);
                    log.info("Google sign-up and sign-in user - {}", user.getEmail());
                    return getSuccessSignInDto(user);
                } else {
                    log.info("Google sign-in exist user - {}", user.getEmail());
                    return getSuccessSignInDto(user);
                }
            } else {
                throw new IllegalArgumentException(ErrorMessage.BAD_GOOGLE_TOKEN);
            }
        } catch (GeneralSecurityException | IOException e) {
            throw new IllegalArgumentException(ErrorMessage.BAD_GOOGLE_TOKEN + ". " + e.getMessage());
        }
    }

}
