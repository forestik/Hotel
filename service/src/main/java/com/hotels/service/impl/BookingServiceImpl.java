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
import java.util.Optional;

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
        roomService.bookingRoom(bookingDto.getRoomId());
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

    @Override
    public List<Booking> findAll() {
        return bookingRepo.findAll();
    }

    @Override
    public Boolean confirm(Long id, String userEmail) {
        Booking booking = getBooking(id, userEmail, true);
        roomService.reserveRoom(booking.getRoom().getId());
        return true;
    }

    @Override
    public Boolean reject(Long id, String userEmail) {
        Booking booking = getBooking(id, userEmail, false);
        roomService.freeRoom(booking.getRoom().getId());
        return false;
    }

    private Booking getBooking(Long id, String userEmail, Boolean condition) {
        User user = userService.findByEmail(userEmail);
        Booking booking = findById(id);
        booking.setAdmin(user);
        booking.setConfirmed(condition);
        bookingRepo.save(booking);
        return booking;
    }

    @Override
    public Boolean cancel(Long id, String userEmail) {
        try {
            bookingRepo.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }


}
