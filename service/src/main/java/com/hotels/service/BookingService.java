package com.hotels.service;

import com.hotels.dto.BookingDto;
import com.hotels.entity.Booking;

import java.util.List;

public interface BookingService {

    /**
     * Booking room.
     *
     * @param bookingDto {@link BookingDto}
     * @param userEmail  email of user
     * @return {@link Booking}
     */
    Booking booking(BookingDto bookingDto, String userEmail);

    /**
     * Find booking by id.
     *
     * @param id of {@link Booking}
     * @return {@link Booking}
     */
    Booking findById(Long id);

    /**
     * Find all booking by user email.
     *
     * @param userEmail email of user
     * @return list of {@link Booking}
     */
    List<Booking> findAll(String userEmail);

    /**
     * Find all booking.
     *
     * @return list of {@link Booking}
     */
    List<Booking> findAll();

    /**
     * Confirm booking.
     *
     * @param id        of {@link Booking}
     * @param userEmail email of admin
     * @return {@link Boolean}
     */
    Boolean confirm(Long id, String userEmail);

    /**
     * Reject booking.
     *
     * @param id        of {@link Booking}
     * @param userEmail email of admin
     * @return {@link Boolean}
     */
    Boolean reject(Long id, String userEmail);

    /**
     * Cancel booking.
     *
     * @param id        of {@link Booking}
     * @param userEmail email of user
     * @return {@link Boolean}
     */
    Boolean cancel(Long id, String userEmail);
}
