package ru.softwarecom.uspn.emulators.ecasa.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaAction;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaPolicy;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaRole;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaUser;
import ru.softwarecom.uspn.emulators.ecasa.domain.dto.*;

@RequiredArgsConstructor
@Slf4j
@Configuration
public class WebConfiguration {
//    private final ObjectMapper mapper;
//
//    @PostConstruct
//    public void setup() {
//        mapper.enable(MapperFeature.DEFAULT_VIEW_INCLUSION);
//    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper mapper = new ModelMapper();

//        mapper.createTypeMap(EcasaActionType.class, ActionTypeDto.class);
//        mapper.createTypeMap(EcasaApplication.class, ApplicationDto.class);
//        mapper.createTypeMap(EcasaResource.class, ResourceDto.class);
//        mapper.createTypeMap(EcasaResourceType.class, ResourceTypeDto.class);

        return mapper;
    }

    @Bean
    public TypeMap<EcasaUser, UserDetailDto> userToDetailDto(ModelMapper mapper) {
        return mapper.createTypeMap(EcasaUser.class, UserDetailDto.class);
    }

    @Bean
    public TypeMap<EcasaUser, UserDto> userToDto(ModelMapper mapper) {
        return mapper.createTypeMap(EcasaUser.class, UserDto.class);
    }

    @Bean
    public TypeMap<EcasaRole, RoleDetailDto> roleToDetailDto(ModelMapper mapper) {
        return mapper.createTypeMap(EcasaRole.class, RoleDetailDto.class);
    }

    @Bean
    public TypeMap<EcasaRole, RoleDto> roleToDto(ModelMapper mapper) {
        return mapper.createTypeMap(EcasaRole.class, RoleDto.class);
    }

    @Bean
    public TypeMap<EcasaPolicy, PolicyDto> policyToDto(ModelMapper mapper) {
        return mapper.createTypeMap(EcasaPolicy.class, PolicyDto.class);
    }

    @Bean
    public TypeMap<EcasaPolicy, PolicyDetailDto> policyToDetailDto(ModelMapper mapper) {
        return mapper.createTypeMap(EcasaPolicy.class, PolicyDetailDto.class);
    }

    @Bean
    public TypeMap<EcasaAction, ActionDto> actionToDto(ModelMapper mapper) {
        return mapper.createTypeMap(EcasaAction.class, ActionDto.class);
    }
}
