package com.hotels.controller;

import com.hotels.dto.BookingDto;
import com.hotels.entity.Booking;
import com.hotels.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/booking")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    /**
     * Method for finding all booking by user email.
     * 
     * @param principal {@link Principal}
     * @return {@link ResponseEntity}
     */
    @GetMapping
    public ResponseEntity<List<Booking>> findAll(@ApiIgnore Principal principal) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.findAll(principal.getName()));
    }

    /**
     * Method for finding all booking.
     *
     * @return {@link ResponseEntity}
     */
    @GetMapping("/all")
    public ResponseEntity<List<Booking>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.findAll());
    }

    /**
     * Method for finding booking by id.
     *
     * @param id of {@link Booking}
     * @return {@link ResponseEntity}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Booking> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.findById(id));
    }

    /**
     * Method for booking room.
     *
     * @param bookingDto {@link BookingDto}
     * @param principal  {@link Principal}
     * @return {@link ResponseEntity}
     */
    @PostMapping
    public ResponseEntity<Booking> booking(@RequestBody BookingDto bookingDto, @ApiIgnore Principal principal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.booking(bookingDto, principal.getName()));
    }

    /**
     * Method for confirming booking.
     *
     * @param id        of {@link Booking}
     * @param principal {@link Principal}
     * @return {@link ResponseEntity}
     */
    @PostMapping("/confirm/{id}")
    public ResponseEntity<Boolean> confirm(@PathVariable Long id, @ApiIgnore Principal principal) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.confirm(id, principal.getName()));
    }

    /**
     * Method for rejecting booking.
     *
     * @param id        of {@link Booking}
     * @param principal {@link Principal}
     * @return {@link ResponseEntity}
     */
    @PostMapping("/reject/{id}")
    public ResponseEntity<Boolean> reject(@PathVariable Long id, @ApiIgnore Principal principal) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.reject(id, principal.getName()));
    }

    /**
     * Method for canceling booking.
     *
     * @param id        of {@link Booking}
     * @param principal {@link Principal}
     * @return {@link ResponseEntity}
     */
    @PostMapping("/cancel/{id}")
    public ResponseEntity<Boolean> cancel(@PathVariable Long id, @ApiIgnore Principal principal) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.cancel(id, principal.getName()));
    }
}
