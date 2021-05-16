package com.hotels.dto;

import lombok.*;

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
