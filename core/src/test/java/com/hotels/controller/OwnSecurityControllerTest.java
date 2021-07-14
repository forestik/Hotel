package com.hotels.controller;

import com.google.gson.Gson;
import com.hotels.dto.OwnSignInDto;
import com.hotels.service.OwnSecurityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class OwnSecurityControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private OwnSecurityController ownSecurityController;

    @Mock
    private OwnSecurityService ownSecurityService;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(ownSecurityController)
                .build();
    }
    @Test
    void singIn() throws Exception {
        OwnSignInDto ownSignInDto = new OwnSignInDto("test@gmail.com", "password");
        Gson gson = new Gson();
        String json = gson.toJson(ownSignInDto);
        mockMvc.perform(post("/ownSecurity/signIn")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(ownSecurityService).signIn(ownSignInDto);
    }
}
