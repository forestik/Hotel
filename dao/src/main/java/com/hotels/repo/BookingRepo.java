package com.hotels.repo;

import com.hotels.entity.Booking;
import com.hotels.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {

    /**
     * Find all booking for customer.
     *
     * @param user {@link User}
     * @return list of {@link Booking}
     */
    List<Booking> findByCustomer(User user);
}
