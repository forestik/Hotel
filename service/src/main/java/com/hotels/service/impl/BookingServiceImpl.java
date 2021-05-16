package com.hotels.service.impl;

import com.hotels.constant.ErrorMessage;
import com.hotels.dto.BookingDto;
import com.hotels.entity.Booking;
import com.hotels.entity.Room;
import com.hotels.entity.User;
import com.hotels.exceptions.NotFoundException;
import com.hotels.repo.BookingRepo;
import com.hotels.service.BookingService;
import com.hotels.service.RoomService;
import com.hotels.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepo bookingRepo;
    private final UserService userService;
    private final RoomService roomService;

    @Override
    public Booking booking(BookingDto bookingDto, String userEmail) {
        User user = userService.findByEmail(userEmail);
        Room room = roomService.findById(bookingDto.getRoomId());
        Booking booking = Booking.builder()
                .customer(user)
                .dateFrom(bookingDto.getDateFrom())
                .dateTo(bookingDto.getDateTo())
                .room(room)
                .build();
        return bookingRepo.save(booking);
    }

    @Override
    public Booking findById(Long id) {
        return bookingRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.BOOKING_NOT_FOUND_BY_ID + id));
    }

    @Override
    public List<Booking> findAll(String userEmail) {
        User user = userService.findByEmail(userEmail);
        return bookingRepo.findByCustomer(user);
    }

}
