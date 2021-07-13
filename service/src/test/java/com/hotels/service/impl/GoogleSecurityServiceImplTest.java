package com.hotels.service.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.hotels.ModelUtils;
import com.hotels.dto.SuccessSignInDto;
import com.hotels.entity.User;
import com.hotels.jwt.JwtTool;
import com.hotels.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GoogleSecurityServiceImplTest {
    @Mock
    private UserService userService;
    @Mock
    private GoogleIdTokenVerifier googleIdTokenVerifier;
    @Mock
    private JwtTool jwtTool;
    @Mock
    private GoogleIdToken googleIdToken;
    @Spy
    private GoogleIdToken.Payload payload;

    @InjectMocks
    private GoogleSecurityServiceImpl googleSecurityService;

    @Test
    void authenticateUserNotNullTest() throws GeneralSecurityException, IOException {
        User user = ModelUtils.getUser();
        when(googleIdTokenVerifier.verify("1234")).thenReturn(googleIdToken);
        when(googleIdToken.getPayload()).thenReturn(payload);
        when(payload.getEmail()).thenReturn("test@mail.com");
        when(userService.findByEmail("test@mail.com")).thenReturn(user);
        SuccessSignInDto result = googleSecurityService.authenticate("1234");
        assertEquals(user.getFirstName(), result.getFirstName());
        assertEquals(user.getId(), result.getUserId());
    }

    @Test
    void authenticateNullUserTest() throws GeneralSecurityException, IOException {
        User user = ModelUtils.getUser();
        user.setId(null);
        user.setFirstName(null);
        when(googleIdTokenVerifier.verify("1234")).thenReturn(googleIdToken);
        when(googleIdToken.getPayload()).thenReturn(payload);
        when(payload.getEmail()).thenReturn("test@mail.com");
        when(userService.findByEmail("test@mail.com")).thenReturn(null);
        when(userService.save(any())).thenReturn(user);
        SuccessSignInDto result = googleSecurityService.authenticate("1234");
        assertNull(result.getUserId());
        assertNull(result.getFirstName());
    }

    @Test
    void authenticationThrowsIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class,
            () -> googleSecurityService.authenticate("1234"));
    }

    @Test
    void authenticationThrowsIllegalArgumentExceptionInCatchBlockTest() throws GeneralSecurityException, IOException {
        when(googleIdTokenVerifier.verify("1234")).thenThrow(GeneralSecurityException.class);
        assertThrows(IllegalArgumentException.class,
            () -> googleSecurityService.authenticate("1234"));
    }
}
