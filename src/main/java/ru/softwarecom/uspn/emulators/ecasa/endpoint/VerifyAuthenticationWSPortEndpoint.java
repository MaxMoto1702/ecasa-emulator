package ru.softwarecom.uspn.emulators.ecasa.endpoint;

import com.rstyle.pfr.ecasa.auth.ws.IsLoggedIn;
import com.rstyle.pfr.ecasa.auth.ws.IsLoggedInResponse;
import com.rstyle.pfr.ecasa.auth.ws.ObjectFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import java.util.Base64;

@SuppressWarnings("rawtypes")
@RequiredArgsConstructor
@Slf4j
@Endpoint
public class VerifyAuthenticationWSPortEndpoint {
    private final ObjectFactory objectFactory = new ObjectFactory();
    private final SessionRepository repository;

    @PayloadRoot(namespace = "http://ws.auth.ecasa.pfr.rstyle.com/", localPart = "isLoggedIn")
    @ResponsePayload
    public JAXBElement<IsLoggedInResponse> isLoggedIn(@RequestPayload JAXBElement<IsLoggedIn> element) throws JAXBException {
        try {
            IsLoggedIn request = element.getValue();
            log.info("isLoggedIn {}", ReflectionToStringBuilder.toString(request));
            Session session = repository.findById(request.getSessionId());
            IsLoggedInResponse response = new IsLoggedInResponse();
            response.setReturn(session != null && !session.isExpired());
            return objectFactory.createIsLoggedInResponse(response);
        } catch (Exception e) {
            log.error("Parsing error", e);
            throw e;
        }
    }

    private String base64Decode(String base64Value) {
        try {
            byte[] decodedCookieBytes = Base64.getDecoder().decode(base64Value);
            return new String(decodedCookieBytes);
        } catch (Exception ex) {
            log.debug("Unable to Base64 decode value: " + base64Value);
            return null;
        }
    }
}

