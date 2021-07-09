package com.hotels.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuccessSignUpDto {
    private Long userId;
    private String firstName;
    private String email;
    private boolean ownRegistrations;
}
