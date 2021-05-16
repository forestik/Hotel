package com.hotels.entity;

import com.hotels.enums.RoomStatus;
import com.hotels.enums.Type;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(nullable = false)
    private Type type;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(nullable = false)
    private RoomStatus roomStatus = RoomStatus.FREE;

    @Column
    private Long pricePerDay;

    @ManyToOne
    private User user;

    @ManyToOne
    private Hotel hotel;

    @OneToOne
    private Booking booking;
}
