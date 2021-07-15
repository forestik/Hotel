package com.hotels.controller;

import com.hotels.dto.HotelDto;
import com.hotels.entity.Hotel;
import com.hotels.service.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/hotel")
@AllArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    /**
     * Method for finding all hotels.
     *
     * @return {@link ResponseEntity} of {@link List} of {@link HotelDto}
     */
    @GetMapping
    public ResponseEntity<List<HotelDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.findAll());
    }

    /**
     * Method for finding hotel by id.
     *
     * @param id id of {@link Hotel}
     * @return {@link ResponseEntity} of {@link HotelDto}
     */
    @GetMapping("/{id}")
    public ResponseEntity<HotelDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.findById(id));
    }

    /**
     * Method for saving hotel.
     *
     * @param hotelDto  {@link HotelDto}
     * @param principal {@link Principal}
     * @return {@link ResponseEntity} of {@link Hotel}
     */
    @PostMapping
    public ResponseEntity<Hotel> save(@RequestBody HotelDto hotelDto, @ApiIgnore Principal principal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.save(hotelDto, principal.getName()));
    }

    /**
     * Method for updating hotel.
     *
     * @param hotelDto {@link HotelDto}
     * @return {@link ResponseEntity} of {@link Hotel}
     */
    @PutMapping
    public ResponseEntity<Hotel> update(@RequestBody HotelDto hotelDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.update(hotelDto));
    }

    /**
     * Method for deleting hotel by id.
     *
     * @param id of {@link Hotel}
     * @return {@link ResponseEntity} of {@link Object}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        hotelService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
