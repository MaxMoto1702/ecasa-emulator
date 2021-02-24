package ru.softwarecom.uspn.emulators.ecasa.domain.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyDetailDto extends PolicyDto {
    protected String description;
    protected Boolean deny;
    protected Boolean semanticAnd;
    protected RoleDto role;
    protected Long applicationId;
    protected String applicationName;
    protected String applicationDisplayName;
    protected Set<ActionDto> actions;
}
