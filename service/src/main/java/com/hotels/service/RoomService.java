package com.hotels.service;

import com.hotels.dto.RoomDto;
import com.hotels.entity.Room;

import java.util.List;

public interface RoomService {
    Room findById(Long roomId);

    Room save(RoomDto roomDto);

    void deleteById(Long id);

    Room update(Long id, RoomDto roomDto);

    List<RoomDto> getRoomsByHotelId(Long hotelId);

    RoomDto getById(Long id);

    void reserveRoom(Long id);

    void bookingRoom(Long roomId);

    void freeRoom(Long id);
}
