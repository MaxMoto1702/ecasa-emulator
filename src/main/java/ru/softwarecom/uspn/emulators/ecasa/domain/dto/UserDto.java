package ru.softwarecom.uspn.emulators.ecasa.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    @NotNull(message = "Логин должен быть заполнен")
    @NotEmpty(message = "Логин должен быть заполнен")
    @Size(min = 3, max = 32, message = "Допустимая длина логина от 3 до 32 символов")
    private String username;

    @NotNull
    private String name;

    @NotNull
    private String firstName;

    @NotNull
    private String middleName;

    @NotNull
    private String lastName;

    @NotNull
    @Pattern(regexp = ".*@.*\\..*", message = "Указанная электронна я почта не соответсвует формату")
    private String email;

    @NotNull
    private String description;
}
