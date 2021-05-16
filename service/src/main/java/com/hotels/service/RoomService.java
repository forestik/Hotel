package com.hotels.service;

import com.hotels.dto.RoomDto;
import com.hotels.entity.Room;

public interface RoomService {
    Room findById(Long roomId);

    Room save(RoomDto roomDto);

    void deleteById(Long id);

    Room update(Long id, RoomDto roomDto);
}
