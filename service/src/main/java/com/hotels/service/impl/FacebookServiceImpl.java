package com.hotels.service.impl;

import com.hotels.constant.ErrorMessage;
import com.hotels.dto.FacebookDataDto;
import com.hotels.dto.SuccessSignInDto;
import com.hotels.entity.User;
import com.hotels.enums.EmailNotification;
import com.hotels.enums.Role;
import com.hotels.enums.UserStatus;
import com.hotels.jwt.JwtTool;
import com.hotels.service.FacebookService;
import com.hotels.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class FacebookServiceImpl implements FacebookService {

    private final UserService userService;
    private final JwtTool jwtTool;

    @Override
    public SuccessSignInDto authenticate(String token) {
        try {
            Facebook facebook = new FacebookTemplate(token);
            String[] fields = {"email", "first_name", "id", "last_name"};
            FacebookDataDto facebookData = facebook.fetchObject("me", FacebookDataDto.class, fields);
            User user = userService.findByEmail(facebookData.getEmail());
            if (user == null) {
                log.info(ErrorMessage.USER_NOT_FOUND_BY_EMAIL + facebookData.getEmail());
                user = createNewUser(facebookData.getEmail(), facebookData.getFirst_name(), facebookData.getFirst_name(), facebookData.getPhoneNumber());
                userService.save(user);
                log.info("Facebook user registered: {}", user.getEmail());
                return getSuccessSignInDto(user);
            } else {
                log.info("Facebook user authenticated: {}", user.getEmail());
                return getSuccessSignInDto(user);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FACEBOOK_TOKEN);
        }
    }

    private User createNewUser(String email, String firstName, String lastName, String phoneNumber) {
        return User.builder()
                .phoneNumber(phoneNumber)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .role(Role.ROLE_USER)
                .dateOfRegistration(LocalDateTime.now())
                .userStatus(UserStatus.ACTIVATED)
                .emailNotification(EmailNotification.DISABLED)
                .refreshTokenKey(jwtTool.generateTokenKey())
                .build();
    }

    private SuccessSignInDto getSuccessSignInDto(User user) {
        String accessToken = jwtTool.createAccessToken(user.getEmail(), user.getRole());
        String refreshToken = jwtTool.createRefreshToken(user);
        return new SuccessSignInDto(user.getId(), accessToken, refreshToken, user.getFirstName());
    }
}
