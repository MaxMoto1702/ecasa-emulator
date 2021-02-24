package ru.softwarecom.uspn.emulators.ecasa.domain.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Data
public class ActionDto {

    @JsonView(PolicyViews.Detail.class)
    protected Long resourceId;

    @JsonView(PolicyViews.Detail.class)
    protected String resourceName;

    @JsonView(PolicyViews.Detail.class)
    protected String resourceDisplayName;

    @JsonView(PolicyViews.Detail.class)
    protected String resourceTypeName;

    @JsonView(PolicyViews.Detail.class)
    protected String typeName;
}
