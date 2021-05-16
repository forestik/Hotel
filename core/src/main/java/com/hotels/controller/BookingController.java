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

    @GetMapping
    public ResponseEntity<List<Booking>> findAll(@ApiIgnore Principal principal) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.findAll(principal.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.findById(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Booking> booking(@RequestBody BookingDto bookingDto, @ApiIgnore Principal principal){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.booking(bookingDto, principal.getName()));
    }
}
