package com.hotels.dto;

import com.hotels.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class UserDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Role role;

    private String phoneNumber;

    private LocalDateTime dateOfRegistration;

    private String profilePicturePath;
}
