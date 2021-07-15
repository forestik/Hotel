package com.hotels.controller;

import com.hotels.dto.EmailDto;
import com.hotels.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class EmailController {
    private final EmailService emailService;

    /**
     * Method for sending email.
     *
     * @param emailDto {@link EmailDto}
     * @return {@link ResponseEntity} of {@link Object}
     */
    @PostMapping()
    public ResponseEntity<Object> sendEmail(@RequestBody EmailDto emailDto) {
        emailService.sendInfoEmail(emailDto);
        return ResponseEntity.ok().build();
    }

}
