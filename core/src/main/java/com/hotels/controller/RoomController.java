package com.hotels.controller;

import com.hotels.dto.RoomDto;
import com.hotels.entity.Room;
import com.hotels.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
@AllArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<Room> create(@RequestBody RoomDto roomDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.save(roomDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> update(@PathVariable Long id, @RequestBody RoomDto roomDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.update(id, roomDto));
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(Long id) {
        roomService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
