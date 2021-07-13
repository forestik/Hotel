package com.hotels.mapper;

import com.hotels.ModelUtils;
import com.hotels.dto.UserDto;
import com.hotels.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserDtoMapperTest {

    @InjectMocks
    private UserDtoMapper userDtoMapper;

    @Test
    void convert() {
        User user = ModelUtils.getUser();
        UserDto userDto = ModelUtils.getUserDto();
        assertEquals(userDtoMapper.convert(user), userDto);
    }
}
