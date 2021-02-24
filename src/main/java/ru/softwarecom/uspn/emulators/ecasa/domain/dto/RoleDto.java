package ru.softwarecom.uspn.emulators.ecasa.domain.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    protected Long id;
    protected String name;
    protected String displayName;
    protected String description;
}
