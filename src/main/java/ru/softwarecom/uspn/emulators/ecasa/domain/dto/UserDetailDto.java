package ru.softwarecom.uspn.emulators.ecasa.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDto extends UserDto {
    private String password;
    private boolean expired;
    private boolean locked;
    private boolean credentialsExpired;
    private boolean enabled;
}
