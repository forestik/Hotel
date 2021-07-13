package com.hotels.service;

import com.hotels.dto.UserDto;
import com.hotels.entity.User;

/**
 * Provides the interface to manage {User} entity.
 *
 */
public interface UserService {
    /**
     * Method that allow you to save new {@link User}.
     *
     * @param user a value of {@link User}
     */
    User save(User user);

    /**
     * Method that allow you to find {@link User} by ID.
     *
     * @param id a value of {@link Long}
     * @return {@link User}
     */
    UserDto findById(Long id);

    /**
     * Method that allow you to find {@link User} by email.
     *
     * @param email a value of {@link String}
     * @return {@link User} with this email.
     */
    User findByEmail(String email);

    /**
     * Updates refresh token for a given user.
     *
     * @param refreshTokenKey - new refresh token key
     * @param id              - user's id
     * @return - number of updated rows
     */
    int updateUserRefreshToken(String refreshTokenKey, Long id);

    /**
     * Updates user data.
     *
     * @param userDto {@link UserDto}
     * @return {@link UserDto}
     */
    User update(UserDto userDto);
}
