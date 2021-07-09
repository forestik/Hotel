package com.hotels.repo;

import com.hotels.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {

    /**
     * Find all rooms in hotel.
     *
     * @param id of hotel
     * @return list of {@link Room}
     */
    List<Room> findByHotelId(Long id);
}
