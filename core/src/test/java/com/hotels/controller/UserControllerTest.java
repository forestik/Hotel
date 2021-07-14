package com.hotels.controller;

import com.google.gson.Gson;
import com.hotels.ModelUtils;
import com.hotels.dto.UserDto;
import com.hotels.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
            .standaloneSetup(userController)
            .build();
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/user/info/{id}", 1))
            .andExpect(status().isOk());
        verify(userService).findById(1L);
    }

    @Test
    void update() throws Exception {
        UserDto userDto = ModelUtils.getUserDto();
        Gson gson = new Gson();
        String json = gson.toJson(userDto);
        mockMvc.perform(patch("/user/info")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        verify(userService).update(userDto);
    }

    @Test
    void findAll() throws Exception {
        UserDto userDto = ModelUtils.getUserDto();
        Gson gson = new Gson();
        String json = gson.toJson(Collections.singletonList(userDto));
        mockMvc.perform(get("/user")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        verify(userService).findAll();
    }
}
