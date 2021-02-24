package ru.softwarecom.uspn.emulators.ecasa.filter.post;

import com.netflix.util.Pair;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.constants.ZuulHeaders;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.util.HTTPRequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;

@SuppressWarnings("WeakerAccess")
//@RequiredArgsConstructor
@Slf4j
public abstract class EcasaHtmlFilter extends PostZuulFilter {
    protected final UrlPathHelper urlPathHelper = new UrlPathHelper();

    @Override
    public boolean shouldFilter() {
        final RequestContext ctx = RequestContext.getCurrentContext();
        return  !urlPathHelper.getPathWithinApplication(ctx.getRequest()).endsWith("/notification") &&
                ctx.getOriginResponseHeaders().stream().anyMatch(header -> Objects.equals(header.first(), "Content-Type") &&
                        header.second() != null &&
                        header.second().contains("text/html"));
    }

    @Override
    public Object run() {
        log.debug("Run");
        try {
            RequestContext context = RequestContext.getCurrentContext();
            context.setResponseBody(modifyBody(context.getResponseBody()));
            return null;
        } catch (Exception e) {
            log.error("Cannot write response because " + e.getMessage(), e);
            throw new RuntimeException("Cannot write response because " + e.getMessage(), e);
        }
    }

    protected abstract String modifyBody(String body);
}

