package com.hotels.service;

import com.hotels.dto.HotelCreateDto;
import com.hotels.dto.HotelDto;
import com.hotels.entity.Hotel;

import java.util.List;

public interface HotelService {
    List<HotelDto> findAll();

    HotelDto findById(Long id);

    Hotel save(HotelDto hotelDto, String userEmail);

    void deleteById(Long id);

    Hotel update(HotelDto hotelDto);

    Hotel findByName(String hotelName);
}
