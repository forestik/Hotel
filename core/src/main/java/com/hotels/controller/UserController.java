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

import java.util.List;

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
     * @return {@link ResponseEntity} of {@link UserDto}
     */
    @GetMapping("/info/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    /**
     * Method fot finding all users.
     *
     * @return {@link ResponseEntity} of {@link List} of {@link UserDto}
     */
    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    /**
     * Method for updating user data.
     *
     * @param userDto {@link UserDto}
     * @return {@link ResponseEntity} of {@link User}
     */
    @PatchMapping("/info")
    public ResponseEntity<User> update(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(userDto));
    }
}
