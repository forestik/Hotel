package com.hotels.dto;

import com.hotels.enums.RoomStatus;
import com.hotels.enums.Type;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class RoomDto {

    private String name;

    @Enumerated(value = EnumType.ORDINAL)
    private Type type;

    private Long pricePerDay;

    private String hotelName;
}
