package ru.softwarecom.uspn.emulators.ecasa.domain.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDetailDto extends RoleDto {
    protected Long policyId;
    protected String policyName;
    protected String policyDisplayName;
}
