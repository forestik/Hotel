package com.hotels.service;

import com.hotels.dto.RoomDto;
import com.hotels.entity.Hotel;
import com.hotels.entity.Room;

import java.util.List;

public interface RoomService {

    /**
     * Find room by id.
     *
     * @param roomId id of {@link Room}
     * @return {@link Room}
     */
    Room findById(Long roomId);

    /**
     * Save room.
     *
     * @param roomDto {@link RoomDto}
     * @return save {@link Room}
     */
    Room save(RoomDto roomDto);

    /**
     * Delete room by id.
     * 
     * @param id of {@link Room}
     */
    void deleteById(Long id);

    /**
     * Update room data.
     *
     * @param id      of {@link Room}
     * @param roomDto {@link RoomDto}
     * @return {@link Room}
     */
    Room update(Long id, RoomDto roomDto);

    /**
     * Find room by hotel id.
     *
     * @param hotelId of {@link Hotel}
     * @return {@link RoomDto}
     */
    List<RoomDto> getRoomsByHotelId(Long hotelId);

    /**
     * Find room by id.
     *
     * @param id of {@link Room}
     * @return {@link RoomDto}
     */
    RoomDto getById(Long id);

    /**
     * Reserve room by id.
     *
     * @param id of {@link Room}
     */
    void reserveRoom(Long id);

    /**
     * Booking room by id.
     *
     * @param roomId of {@link Room}
     */
    void bookingRoom(Long roomId);

    /**
     * Make status of room FREE.
     *
     * @param id of {@link Room}
     */
    void freeRoom(Long id);
}
