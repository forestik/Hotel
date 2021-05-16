package com.hotels.service.impl;

import com.hotels.constant.ErrorMessage;
import com.hotels.dto.HotelDto;
import com.hotels.entity.Hotel;
import com.hotels.entity.User;
import com.hotels.exceptions.UserDontHavePermissionException;
import com.hotels.repo.HotelRepo;
import com.hotels.service.HotelService;
import com.hotels.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.hotels.exceptions.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepo hotelRepo;
    private final UserService userService;

    @Override
    public List<Hotel> findAll() {
        return hotelRepo.findAll();
    }

    @Override
    public Hotel findById(Long id) {
        return hotelRepo.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.HOTEL_NOT_FOUND_BY_ID + id));
    }

    @Override
    public Hotel save(HotelDto hotelDto, String userEmail) {
        User user = userService.findByEmail(userEmail);
        Hotel hotel = Hotel.builder()
                .name(hotelDto.getName())
                .admins(List.of(user))
                .build();
        return hotelRepo.save(hotel);
    }

    @Override
    public void deleteById(Long id) {
        hotelRepo.deleteById(id);
    }

    @Override
    public Hotel update(HotelDto hotelDto, String userEmail) {
        User user = userService.findByEmail(userEmail);
        Hotel hotel = findByName(hotelDto.getName());
        if(hotel.getAdmins().contains(user)){
            hotel.setName(hotelDto.getName());
            return hotelRepo.save(hotel);
        }
        throw new UserDontHavePermissionException(user.getFirstName() + ErrorMessage.USER_DONT_HAVE_PERMISSION);
    }

    @Override
    public Hotel findByName(String hotelName) {
        return hotelRepo.findByName(hotelName).orElse(null);
    }
}
