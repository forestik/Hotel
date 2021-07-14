package com.hotels.controller;

import com.hotels.dto.RoomDto;
import com.hotels.entity.Hotel;
import com.hotels.entity.Room;
import com.hotels.service.RoomService;
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

import java.util.List;

@RestController
@RequestMapping("/room")
@AllArgsConstructor
public class RoomController {

    private final RoomService roomService;

    /**
     * Method for finding all rooms in hotel.
     *
     * @param id of {@link Hotel}
     * @return {@link ResponseEntity} of {@link List} of {@link RoomDto}
     */
    @GetMapping("/hotel/{id}")
    public ResponseEntity<List<RoomDto>> getRoomsByHotelId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(roomService.getRoomsByHotelId(id));
    }

    /**
     * Method for finding room by id.
     *
     * @param id of {@link Room}
     * @return {@link ResponseEntity} of {@link RoomDto}
     */
    @GetMapping("/{id}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(roomService.getById(id));
    }

    /**
     * Method for saving room.
     *
     * @param roomDto {@link RoomDto}
     * @return {@link ResponseEntity} of {@link Room}
     */
    @PostMapping
    public ResponseEntity<Room> create(@RequestBody RoomDto roomDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.save(roomDto));
    }

    /**
     * Method for updating room by id.
     *
     * @param id      of {@link Room}
     * @param roomDto {@link RoomDto}
     * @return {@link ResponseEntity} of {@link Room}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Room> update(@PathVariable Long id, @RequestBody RoomDto roomDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.update(id, roomDto));
    }

    /**
     * Method for deleting room by id.
     *
     * @param id of {@link Room}
     * @return {@link ResponseEntity} of {@link Object}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        roomService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
