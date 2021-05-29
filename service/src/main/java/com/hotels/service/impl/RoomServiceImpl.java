package com.hotels.service.impl;

import com.hotels.constant.ErrorMessage;
import com.hotels.dto.RoomDto;
import com.hotels.entity.Hotel;
import com.hotels.entity.Room;
import com.hotels.enums.RoomStatus;
import com.hotels.exceptions.NotFoundException;
import com.hotels.repo.RoomRepo;
import com.hotels.service.HotelService;
import com.hotels.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepo roomRepo;
    private final HotelService hotelService;
    private final ModelMapper modelMapper;

    @Override
    public Room findById(Long roomId) {
        return roomRepo.findById(roomId)
                .orElseThrow(() ->
                        new NotFoundException(ErrorMessage.ROOM_NOT_FOUND_BY_ID + roomId));
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
                    .roomStatus(roomDto.getRoomStatus())
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
    @Transactional
    public Room update(Long id, RoomDto roomDto) {
        Room room = roomRepo.findById(id).orElseThrow(() ->
                new NotFoundException(ErrorMessage.ROOM_NOT_FOUND_BY_ID + id));
        room.setName(roomDto.getName());
        room.setPricePerDay(roomDto.getPricePerDay());
        room.setType(roomDto.getType());
        room.setRoomStatus(roomDto.getRoomStatus());
        return roomRepo.save(room);
    }

    @Override
    public List<RoomDto> getRoomsByHotelId(Long hotelId) {
        return modelMapper.map(roomRepo.findByHotelId(hotelId),
                new TypeToken<List<RoomDto>>(){}.getType());
    }

    @Override
    public RoomDto getById(Long id) {
        return modelMapper.map(roomRepo.findById(id).orElseThrow(() ->
                new NotFoundException(ErrorMessage.ROOM_NOT_FOUND_BY_ID + id)), RoomDto.class);
    }

    @Override
    public void reserveRoom(Long id) {
        Room room = roomRepo.findById(id).orElseThrow(() ->
                new NotFoundException(ErrorMessage.ROOM_NOT_FOUND_BY_ID + id));
        room.setRoomStatus(RoomStatus.RESERVED);
        roomRepo.save(room);
    }

    @Override
    public void bookingRoom(Long id) {
        Room room = roomRepo.findById(id).orElseThrow(() ->
                new NotFoundException(ErrorMessage.ROOM_NOT_FOUND_BY_ID + id));
        room.setRoomStatus(RoomStatus.BOOKING);
        roomRepo.save(room);
    }

    @Override
    public void freeRoom(Long id) {
        Room room = roomRepo.findById(id).orElseThrow(() ->
                new NotFoundException(ErrorMessage.ROOM_NOT_FOUND_BY_ID + id));
        room.setRoomStatus(RoomStatus.FREE);
        roomRepo.save(room);
    }
}
