package com.hotels.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelDto {

    private Long id;

    private String name;

    private String address;

    private Integer count;

    private String email;

    private String phone;
}
