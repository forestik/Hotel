package com.hotels.dto;

import com.hotels.entity.User;
import com.hotels.enums.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Class represents Facebook user data.
 *
 */
@Data
@Getter
@Setter
public class FacebookDataDto {
    private String email;
    private String phoneNumber;
    private Long id;
    private String firstName;
    private String lastName;

    /**
     * Converts Facebook user to hotel user.
     *
     * @return user
     */
    public User toUser() {
        User user = new User();
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(Role.ROLE_USER);
        return user;
    }
}