package com.hotels.mapper;

import com.hotels.dto.RoomDto;
import com.hotels.entity.Room;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class RoomDtoMapper extends AbstractConverter<Room, RoomDto> {

    @Override
    public RoomDto convert(Room room) {

        return RoomDto.builder()
            .id(room.getId())
            .name(room.getName())
            .hotelName(room.getHotel().getName())
            .type(room.getType())
            .pricePerDay(room.getPricePerDay())
            .roomStatus(room.getRoomStatus())
            .build();
    }
}
