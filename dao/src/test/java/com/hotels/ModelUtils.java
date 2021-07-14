package com.hotels;

import com.hotels.dto.OwnSignUpDto;
import com.hotels.dto.SuccessSignUpDto;
import com.hotels.dto.UserDto;
import com.hotels.entity.User;
import com.hotels.entity.VerifyEmail;
import com.hotels.enums.Role;

import java.time.LocalDateTime;

public class ModelUtils {

    public static User getUser() {
        return User.builder()
            .id(1L)
            .email("test@gmail.com")
            .firstName("Test")
            .lastName("Test")
            .profilePicturePath("test")
            .role(Role.ROLE_USER)
            .phoneNumber("+380*********")
            .verifyEmail(new VerifyEmail())
            .dateOfRegistration(LocalDateTime.of(2021, 10, 10, 10, 10, 10))
            .build();
    }

    public static UserDto getUserDto() {
        return UserDto.builder()
            .id(1L)
            .firstName("Test")
            .lastName("Test")
            .profilePicturePath("test")
            .role(Role.ROLE_USER)
            .phoneNumber("+380*********")
            .email("test@gmail.com")
            .dateOfRegistration(LocalDateTime.of(2021, 10, 10, 10, 10, 10))
            .build();
    }
}
