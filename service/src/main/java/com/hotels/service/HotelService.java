package com.hotels.service;

import com.hotels.dto.HotelDto;
import com.hotels.entity.Hotel;

import java.util.List;

public interface HotelService {
    List<Hotel> findAll();

    Hotel findById(Long id);

    Hotel save(HotelDto hotelDto, String userEmail);

    void deleteById(Long id);

    Hotel update(HotelDto hotelDto,String userEmail);

    Hotel findByName(String hotelName);
}
