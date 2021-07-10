package com.hotels.service.impl;

import com.hotels.dto.OwnSignUpDto;
import com.hotels.dto.SuccessSignInDto;
import com.hotels.entity.User;
import com.hotels.enums.EmailNotification;
import com.hotels.enums.Role;
import com.hotels.enums.UserStatus;
import com.hotels.jwt.JwtTool;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public abstract class AuthService {
    private final JwtTool jwtTool;

    protected SuccessSignInDto getSuccessSignInDto(User user) {
        String accessToken = jwtTool.createAccessToken(user.getEmail(), user.getRole());
        String refreshToken = jwtTool.createRefreshToken(user);
        return new SuccessSignInDto(user.getId(), accessToken, refreshToken, user.getFirstName());
    }

    protected User createNewRegisteredUser(OwnSignUpDto ownSignUpDto, String profilePicture) {
        return User.builder()
                .firstName(ownSignUpDto.getFirstName())
                .lastName(ownSignUpDto.getLastName())
                .email(ownSignUpDto.getEmail())
                .role(Role.ROLE_USER)
                .dateOfRegistration(LocalDateTime.now())
                .refreshTokenKey(jwtTool.generateTokenKey())
                .profilePicturePath(profilePicture)
                .userStatus(UserStatus.ACTIVATED)
                .emailNotification(EmailNotification.DISABLED)
                .build();
    }
}
