package com.hotels.controller;

import com.google.gson.Gson;
import com.hotels.ModelUtils;
import com.hotels.dto.EmailDto;
import com.hotels.entity.User;
import com.hotels.service.EmailService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class EmailControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private EmailController emailController;

    @Mock
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders
            .standaloneSetup(emailController)
            .build();
    }

    @Test
    void sendEmail() throws Exception {
        User user = ModelUtils.getUser();
        EmailDto emailDto = EmailDto.builder()
            .email(user.getEmail())
            .userName(user.getFirstName() + " " + user.getLastName())
            .title("Test")
            .text("Test")
            .build();
        Gson gson = new Gson();
        String json = gson.toJson(emailDto);
        mockMvc.perform(post("/mail")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        verify(emailService).sendInfoEmail(emailDto);
    }
}
