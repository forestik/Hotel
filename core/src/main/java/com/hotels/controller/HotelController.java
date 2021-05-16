package com.hotels.controller;

import com.hotels.dto.HotelDto;
import com.hotels.entity.Hotel;
import com.hotels.service.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/hotel")
@AllArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping
    public ResponseEntity<List<Hotel>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Hotel> save(@RequestBody HotelDto hotelDto, @ApiIgnore Principal principal){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.save(hotelDto, principal.getName()));
    }

    @PutMapping
    public ResponseEntity<Hotel> update(@RequestBody HotelDto hotelDto, @ApiIgnore Principal principal){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.update(hotelDto, principal.getName()));
    }

    @DeleteMapping
   public ResponseEntity<Object> delete(Long id) {
       hotelService.deleteById(id);
       return ResponseEntity.ok().build();
    }
}
