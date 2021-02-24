package ru.softwarecom.uspn.emulators.ecasa.filter.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.stereotype.Component;

import static ru.softwarecom.uspn.emulators.ecasa.filter.support.FilterConstants.ECASA_HTML_HEADER_FILTER_ORDER;

@SuppressWarnings("WeakerAccess")
//@RequiredArgsConstructor
@Slf4j
@Component
public class EcasaHtmlHeaderFilter extends EcasaHtmlFilter {

    @Override
    public int filterOrder() {
        return ECASA_HTML_HEADER_FILTER_ORDER;
    }

    @Override
    protected String modifyBody(String body) {
        log.trace("Body: \n" + body);
        body = body.replaceAll(
                "<body class=\"container-fluid\">",
                "<body class=\"container-fluid\"><div>Эмулятор ЕЦАСА</div>"
        );
        log.trace("Modified body: \n" + body);
        return body;
    }
}

