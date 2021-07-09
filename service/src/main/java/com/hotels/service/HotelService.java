package com.hotels.service;

import com.hotels.dto.HotelDto;
import com.hotels.entity.Hotel;

import java.util.List;

public interface HotelService {

    /**
     * Find all hotel.
     *
     * @return list of {@link HotelDto}
     */
    List<HotelDto> findAll();

    /**
     * Find hotel by id.
     *
     * @param id of {@link Hotel}
     * @return {@link HotelDto}
     */
    HotelDto findById(Long id);

    /**
     * Save hotel.
     *
     * @param hotelDto  {@link HotelDto}
     * @param userEmail email of user
     * @return {@link Hotel}
     */
    Hotel save(HotelDto hotelDto, String userEmail);

    /**
     * Delete hotel by id.
     *
     * @param id of {@link Hotel}
     */
    void deleteById(Long id);

    /**
     * Update data for hotel.
     *
     * @param hotelDto {@link HotelDto}
     * @return {@link Hotel}
     */
    Hotel update(HotelDto hotelDto);

    /**
     * Find hotel by name.
     *
     * @param hotelName name of hotel
     * @return {@link Hotel}
     */
    Hotel findByName(String hotelName);
}
