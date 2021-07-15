package com.hotels.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Builder
public class EmailDto {

    private Long id;

    private String email;

    private String text;

    private String title;

    private String userName;

    private String token;
}
