package com.hotels.entity;

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
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String address;

    @Column
    private Integer count = 10;

    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(mappedBy = "hotel")
    private List<User> admins = new ArrayList<>();
}
