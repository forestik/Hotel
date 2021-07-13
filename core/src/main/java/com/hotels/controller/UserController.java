package com.hotels.controller;

import com.hotels.dto.UserDto;
import com.hotels.entity.User;
import com.hotels.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Validated
public class UserController {
    private final UserService userService;

    /**
     * Method for finding user by id user.
     *
     * @param id id of {@link User}
     * @return {@link ResponseEntity}
     */
    @GetMapping("/info/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    /**
     * Method for updating user data.
     *
     * @param userDto {@link UserDto}
     * @return {@link ResponseEntity}
     */
    @PatchMapping("/info")
    public ResponseEntity<User> update(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(userDto));
    }
}
