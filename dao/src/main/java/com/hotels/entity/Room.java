package com.hotels.entity;

import com.hotels.enums.RoomStatus;
import com.hotels.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

    @ManyToOne()
    private Hotel hotel;

    @OneToOne
    private Booking booking;
}
