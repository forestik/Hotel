package com.hotels.mapper;

import com.hotels.dto.UserDto;
import com.hotels.entity.User;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper extends AbstractConverter<User, UserDto> {

    @Override
    protected UserDto convert(User user) {
        return UserDto.builder()
            .id(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .dateOfRegistration(user.getDateOfRegistration())
            .phoneNumber(user.getPhoneNumber())
            .role(user.getRole())
            .profilePicturePath(user.getProfilePicturePath())
            .build();
    }
}
