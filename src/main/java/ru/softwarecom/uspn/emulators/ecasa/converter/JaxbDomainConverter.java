package ru.softwarecom.uspn.emulators.ecasa.converter;

import com.rstyle.pfr.ecasa.integration.jaxb.ApplicationType;
import com.rstyle.pfr.ecasa.integration.jaxb.ResourceActionsType;
import com.rstyle.pfr.ecasa.integration.jaxb.RoleType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.softwarecom.uspn.emulators.ecasa.domain.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JaxbDomainConverter {

    public EcasaApplication convert(ApplicationType applicationType) {

        return null;
    }
}
