package com.hotels.service;

import com.hotels.dto.BookingDto;
import com.hotels.entity.Booking;

import java.util.List;

public interface BookingService {
    Booking booking(BookingDto bookingDto, String userEmail);

    Booking findById(Long id);

    List<Booking> findAll(String userEmail);

    List<Booking> findAll();

    Boolean confirm(Long id, String userEmail);

    Boolean reject(Long id, String userEmail);

    Boolean cancel(Long id, String userEmail);
}
