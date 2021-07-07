package com.hotels;

import com.hotels.entity.User;
import com.hotels.entity.VerifyEmail;
import com.hotels.enums.Role;

import java.time.LocalDateTime;

public class ModelUtils {

    public static User getUser() {
        return User.builder()
            .id(1L)
            .email("test@gmail.com")
            .firstName("test")
            .lastName("test")
            .role(Role.ROLE_USER)
            .verifyEmail(new VerifyEmail())
            .dateOfRegistration(LocalDateTime.now())
            .build();
    }
}
