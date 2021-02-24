package ru.softwarecom.uspn.emulators.ecasa.contoller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaRole;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaUser;
import ru.softwarecom.uspn.emulators.ecasa.domain.MapInfo;
import ru.softwarecom.uspn.emulators.ecasa.repository.EcasaUserRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@SuppressWarnings("rawtypes")
@RequiredArgsConstructor
@RestController
@RequestMapping("eca-ws")
public class EcaWsController {
    private static final Logger log = getLogger(EcaWsController.class);

    private final EntityManager entityManager;
    private final EcasaUserRepository userRepository;

    @RequestMapping(path = "map/info", method = RequestMethod.GET)
    public ResponseEntity mapInfo(
            @RequestParam String userGuid,
            @RequestParam String appId
    ) {
        log.debug(
                "Request map info for user GUID [ {} ] and application ID [ {} ]",
                userGuid,
                appId
        );
        EcasaUser u = userRepository.findByUsername(userGuid);
        if (u == null) {
            log.error("Not found user by GUID [ " + userGuid + " ]");
            return notFound().build();
        }
        Query query = entityManager.createNativeQuery("" +
                "select er.NAME || '.' || EAT.NAME as NAME \n" +
                "from USPNECASA.POLICY ep \n" +
                "left join USPNECASA.ACTION EA on ep.ID = EA.POLICY_ID \n" +
                "left join USPNECASA.ACTION_TYPE EAT on EAT.ID = EA.ACTION_TYPE_ID \n" +
                "left join USPNECASA.RESOURCE ER on ER.ID = EA.RESOURCE_ID \n" +
                "where ep.ROLE_ID in (:roleIds)");
        query.setParameter("roleIds", u.getRoles().stream().map(EcasaRole::getId).collect(Collectors.toList()));
        List<String> result = query.getResultList();
        return ok(result.stream().map(MapInfo::new).collect(Collectors.toList()));

//        //noinspection ArraysAsListWithZeroOrOneArgument
//        return ResponseEntity.ok(Arrays.asList(
//                new LinkedHashMap<String, Object>() {{
//                    put("name", "ADMIN_QUEUE.view");
//                }}
//        ));
    }


    @RequestMapping(path = "pdp/getObligations", method = RequestMethod.GET)
    public ResponseEntity pdpGetObligations(
            @RequestParam String userGuid
    ) {
        log.debug(
                "Request PDP get o for user GUID [ {} ]",
                userGuid
        );
        return ok(Collections.emptyList());
    }
}

