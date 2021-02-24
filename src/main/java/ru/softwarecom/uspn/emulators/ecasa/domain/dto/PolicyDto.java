package ru.softwarecom.uspn.emulators.ecasa.domain.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyDto {
    protected Long id;
    protected String name;
    protected String displayName;
}
