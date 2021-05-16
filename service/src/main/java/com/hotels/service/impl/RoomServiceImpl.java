package com.hotels.service.impl;

import com.hotels.constant.ErrorMessage;
import com.hotels.dto.RoomDto;
import com.hotels.entity.Hotel;
import com.hotels.entity.Room;
import com.hotels.exceptions.NotFoundException;
import com.hotels.repo.RoomRepo;
import com.hotels.service.HotelService;
import com.hotels.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepo roomRepo;
    private final HotelService hotelService;

    @Override
    public Room findById(Long roomId) {
        return roomRepo.findById(roomId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.ROOM_NOT_FOUND_BY_ID + roomId));
    }

    @Override
    public Room save(RoomDto roomDto) {
        Hotel hotel = hotelService.findByName(roomDto.getHotelName());
        if (hotel != null) {
            Room room = Room.builder()
                    .hotel(hotel)
                    .name(roomDto.getName())
                    .pricePerDay(roomDto.getPricePerDay())
                    .type(roomDto.getType())
                    .build();
            return roomRepo.save(room);
        }
        else throw new NotFoundException(ErrorMessage.HOTEL_NOT_FOUND_BY_NAME + roomDto.getHotelName());
    }

    @Override
    public void deleteById(Long id) {
        roomRepo.deleteById(id);
    }

    @Override
    public Room update(Long id, RoomDto roomDto) {
        Room room = roomRepo.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.ROOM_NOT_FOUND_BY_ID + id));
        room.setName(roomDto.getName());
        room.setPricePerDay(room.getPricePerDay());
        room.setType(roomDto.getType());
        return roomRepo.save(room);
    }
}
