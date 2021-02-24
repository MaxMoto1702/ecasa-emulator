package ru.softwarecom.uspn.emulators.ecasa.filter.post;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;

@Slf4j
public abstract class TraceFilter extends PostZuulFilter {
    protected final UrlPathHelper urlPathHelper = new UrlPathHelper();

    @Override
    public boolean shouldFilter() {
        return log.isTraceEnabled();
    }

    @Override
    public Object run() throws ZuulException {
        try {
            log.trace("URL [ {} ]", extractUrl());
            log.trace("Request headers [ {} ]", extractRequestHeaders());
            log.trace("Request body [ {} ]", extractRequestBody());
            log.trace("Response headers [ {} ]", extractResponseHeaders());
            log.trace("Response body [ {} ]", extractResponseBody());
        } catch (Exception e) {
            log.error("Cannot run filter because [ " + e.getMessage() + " ]", e);
        }
        return null;
    }

    private String extractResponseBody() throws IOException {
        final RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.getResponseBody() != null ?
                ctx.getResponseBody() :
                "Body is stream or empty";
    }

    private MultiValueMap<String, String> extractResponseHeaders() {
        final RequestContext ctx = RequestContext.getCurrentContext();
        final HttpServletResponse response = ctx.getResponse();
        final MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        for (String header : response.getHeaderNames()) {
            for (String value : response.getHeaders(header)) {
                headers.add(header, value);
            }
        }
        return headers;
    }

    private String extractRequestBody() throws IOException {
        final RequestContext ctx = RequestContext.getCurrentContext();
        final HttpServletRequest request = ctx.getRequest();
        return IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
    }

    private MultiValueMap<String, String> extractRequestHeaders() {
        final RequestContext ctx = RequestContext.getCurrentContext();
        final HttpServletRequest request = ctx.getRequest();
        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            Enumeration<String> values = request.getHeaders(header);
            while (values.hasMoreElements()) {
                headers.add(header, values.nextElement());
            }
        }
        return headers;
    }

    private String extractUrl() {
        final RequestContext ctx = RequestContext.getCurrentContext();
        final HttpServletRequest request = ctx.getRequest();
        return urlPathHelper.getPathWithinApplication(request);
    }
}
