package ru.softwarecom.uspn.emulators.ecasa.contoller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaPolicy;
import ru.softwarecom.uspn.emulators.ecasa.domain.dto.PolicyDetailDto;
import ru.softwarecom.uspn.emulators.ecasa.domain.dto.PolicyDto;
import ru.softwarecom.uspn.emulators.ecasa.domain.dto.PolicyViews;
import ru.softwarecom.uspn.emulators.ecasa.repository.EcasaPolicyRepository;

import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping(value = "api/policies", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class PolicyController {

    private final EcasaPolicyRepository policyRepository;
    private final ModelMapper mapper;
    private final TypeMap<EcasaPolicy, PolicyDto> policyToDto;
    private final TypeMap<EcasaPolicy, PolicyDetailDto> policyToDetailDto;

    @GetMapping
    public ResponseEntity<Page<PolicyDto>> list(Pageable pageable) {
        log.debug("Get policies by [ {} ]", pageable);
        Page<EcasaPolicy> all = policyRepository.findAll(pageable);
        return ok(all
                        .map(policy -> mapper.map(policy, PolicyDto.class))
//                        .map(policyToDto::map)
//                        .map(policy -> new PolicyDto(
//                                policy.getId(),
//                                policy.getName(),
//                                policy.getDisplayName()
//                        ))
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<PolicyDto> get(@PathVariable Long id) {
        log.debug("Get policy by ID [ {} ]", id);
        if (!policyRepository.existsById(id)) {
            log.error("EcasaPolicy not found by ID [ " + id + " ]");
            return notFound().build();
        }
        final EcasaPolicy policy = policyRepository.getOne(id);
        return ok(policyToDetailDto.map(policy));
    }
}
