package ru.softwarecom.uspn.emulators.ecasa.filter.post;

import com.netflix.util.Pair;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.function.Predicate;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;
import static ru.softwarecom.uspn.emulators.ecasa.filter.support.FilterConstants.STATIC_RESPONSE_FILTER_ORDER;

@Component
public class StaticResponseFilter extends PostZuulFilter {
    protected final UrlPathHelper urlPathHelper = new UrlPathHelper();

    @Override
    public int filterOrder() {
        return STATIC_RESPONSE_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        final RequestContext ctx = RequestContext.getCurrentContext();
        return  !urlPathHelper.getPathWithinApplication(ctx.getRequest()).endsWith("/notification") &&
                ctx.getOriginResponseHeaders().stream().anyMatch(header -> Objects.equals(header.first(), "Content-Type") &&
                header.second() != null &&
                header.second().contains("text/html"));
    }

    @Override
    public Object run() throws ZuulException {
        try {
            final RequestContext ctx = RequestContext.getCurrentContext();
            ctx.setResponseBody(IOUtils.toString(ctx.getResponseDataStream(), StandardCharsets.UTF_8));
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
