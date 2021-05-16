package com.hotels.dto;

import com.hotels.enums.Role;
import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserRoleDto {
    @NotNull
    private Long id;

    @NotNull
    private Role role;
}
