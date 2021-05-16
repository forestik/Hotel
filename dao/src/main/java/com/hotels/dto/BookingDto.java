package com.hotels.dto;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class BookingDto {

    private Long roomId;

    private Date dateFrom;

    private Date dateTo;
}
