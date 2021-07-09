package com.hotels.service.impl;

import com.hotels.constant.ErrorMessage;
import com.hotels.dto.HotelDto;
import com.hotels.entity.Hotel;
import com.hotels.entity.User;
import com.hotels.exceptions.NotFoundException;
import com.hotels.repo.HotelRepo;
import com.hotels.service.HotelService;
import com.hotels.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepo hotelRepo;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public List<HotelDto> findAll() {
        return modelMapper.map(hotelRepo.findAll(),
            new TypeToken<List<HotelDto>>() {
            }.getType());
    }

    @Override
    public HotelDto findById(Long id) {
        return modelMapper.map(hotelRepo.findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.HOTEL_NOT_FOUND_BY_ID + id)),
            HotelDto.class);
    }

    @Override
    public Hotel save(HotelDto hotelDto, String userEmail) {
        User user = userService.findByEmail(userEmail);
        Hotel hotel = Hotel.builder()
            .name(hotelDto.getName())
            .address(hotelDto.getAddress())
            .email(hotelDto.getEmail())
            .phone(hotelDto.getPhone())
            .admins(List.of(user))
            .count(0)
            .rooms(new ArrayList<>())
            .build();
        return hotelRepo.save(hotel);
    }

    @Override
    public void deleteById(Long id) {
        hotelRepo.deleteById(id);
    }

    @Override
    public Hotel update(HotelDto hotelDto) {
        Hotel hotel = findByName(hotelDto.getName());
        hotel.setName(hotelDto.getName());
        hotel.setAddress(hotelDto.getAddress());
        hotel.setCount(hotelDto.getCount());
        hotel.setEmail(hotelDto.getEmail());
        hotel.setPhone(hotelDto.getPhone());
        return hotelRepo.save(hotel);
    }

    @Override
    public Hotel findByName(String hotelName) {
        return hotelRepo.findByName(hotelName).orElse(null);
    }
}
