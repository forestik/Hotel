package com.hotels.service.impl;

import com.hotels.ModelUtils;
import com.hotels.dto.UserDto;
import com.hotels.entity.User;
import com.hotels.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepo userRepo;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void save() {
        User user = ModelUtils.getUser();
        User user2 = ModelUtils.getUser();
        user2.setId(null);
        when(userRepo.save(user2)).thenReturn(user);
        assertEquals(userService.save(user2), user);
        verify(userRepo).save(user2);
    }

    @Test
    void findById() {
        User user = ModelUtils.getUser();
        UserDto userDto = ModelUtils.getUserDto();
        when(userRepo.findById(user.getId())).thenReturn(Optional.of(user));
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
        assertEquals(userService.findById(user.getId()), userDto);
    }

    @Test
    void findByEmail() {
        User user = ModelUtils.getUser();
        when(userRepo.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        assertEquals(userService.findByEmail(user.getEmail()), user);
    }

    @Test
    void updateUserRefreshToken() {
        when(userRepo.updateUserRefreshToken("token", 1L)).thenReturn(1);
        assertEquals(1, userService.updateUserRefreshToken("token", 1L));
    }

    @Test
    void update() {
        User user = ModelUtils.getUser();
        UserDto userDto = ModelUtils.getUserDto();
        when(userRepo.findById(user.getId())).thenReturn(Optional.of(user));
        user.setPhoneNumber(userDto.getPhoneNumber());
        when(userRepo.save(user)).thenReturn(user);
        assertEquals(userService.update(userDto), user);

    }
}
