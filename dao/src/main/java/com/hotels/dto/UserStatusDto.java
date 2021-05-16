package com.hotels.dto;

import com.hotels.enums.UserStatus;
import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserStatusDto {
    @NotNull
    private Long id;

    @NotNull
    private UserStatus userStatus;
}
