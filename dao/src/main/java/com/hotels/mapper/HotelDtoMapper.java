package com.hotels.mapper;

import com.hotels.dto.HotelDto;
import com.hotels.entity.Hotel;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class HotelDtoMapper extends AbstractConverter<Hotel, HotelDto> {

    @Override
    public HotelDto convert(Hotel hotel) {

        return HotelDto.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .address(hotel.getAddress())
                .count(hotel.getCount())
                .email(hotel.getEmail())
                .phone(hotel.getPhone())
                .build();
    }
}
