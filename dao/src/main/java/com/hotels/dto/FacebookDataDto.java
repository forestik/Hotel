package com.hotels.dto;

import com.hotels.entity.User;
import com.hotels.enums.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class FacebookDataDto {
    private String email;
    private String phoneNumber;
    private Long id;
    private String first_name;
    private String last_name;


    public User toUser() {
        User user = new User();
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setId(id);
        user.setFirstName(first_name);
        user.setLastName(last_name);
        user.setRole(Role.ROLE_USER);
        return user;
    }
}
