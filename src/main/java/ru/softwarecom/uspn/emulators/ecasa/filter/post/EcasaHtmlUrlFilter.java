package ru.softwarecom.uspn.emulators.ecasa.filter.post;

import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

import static ru.softwarecom.uspn.emulators.ecasa.filter.support.FilterConstants.ECASA_HTML_URL_FILTER_ORDER;

@SuppressWarnings("WeakerAccess")
@Slf4j
@Component
public class EcasaHtmlUrlFilter extends EcasaHtmlFilter {

    protected final RouteLocator routeLocator;

    public EcasaHtmlUrlFilter(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public int filterOrder() {
        return ECASA_HTML_URL_FILTER_ORDER;
    }

    @Override
    protected String modifyBody(String body) {
        final RequestContext ctx = RequestContext.getCurrentContext();
        final HttpServletRequest request = ctx.getRequest();
        final String pathWithinApplication = urlPathHelper.getPathWithinApplication(request);
        final Route route = routeLocator.getMatchingRoute(
                pathWithinApplication
        );
        String proxyLocation;
        String proxyPath;
        try {
            proxyLocation = route.getLocation();
            proxyPath = new URI(proxyLocation).getPath();
        } catch (URISyntaxException e) {
            log.error("Cannot detect proxy location or path because " + e.getMessage(), e);
            throw new RuntimeException("Cannot detect proxy location or path because " + e.getMessage(), e);
        }
        String prefix = route.getPrefix();

        log.debug("Proxy location [ {} ]. Proxy path [ {} ]. Prefix [ {} ]. ", proxyLocation, proxyPath, prefix);

        if (proxyLocation.endsWith("/")) {
            log.debug("Remove last slash in proxy location");
            proxyLocation = proxyLocation.replaceAll("/$", "");
        }

        if (proxyPath.endsWith("/")) {
            log.debug("Remove last slash in proxy path");
            proxyPath = proxyPath.replaceAll("/$", "");
        }

        if (prefix.endsWith("/")) {
            log.debug("Remove last slash in full path");
            prefix = prefix.replaceAll("/$", "");
        }

        log.trace("Body: \n" + body);

        body = body
                .replaceAll("'" + proxyLocation, "'" + prefix)
                .replaceAll("'" + proxyPath, "'" + prefix)
                .replaceAll('"' + proxyLocation, '"' + prefix)
                .replaceAll('"' + proxyPath, '"' + prefix)
        ;
        log.trace("Modified body: \n" + body);
        return body;
    }
}

