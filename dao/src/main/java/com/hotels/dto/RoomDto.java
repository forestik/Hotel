package com.hotels.dto;

import com.hotels.enums.RoomStatus;
import com.hotels.enums.Type;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDto {

    private Long id;

    private String name;

    @Enumerated(value = EnumType.ORDINAL)
    private Type type;

    private Long pricePerDay;

    private String hotelName;

    private RoomStatus roomStatus;
}
