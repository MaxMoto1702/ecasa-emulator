package ru.softwarecom.uspn.emulators.ecasa.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.post.LocationRewriteFilter;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.filters.post.SendResponseFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Configuration
@EnableZuulProxy
public class ZuulProxyConfiguration {

    @Bean
    public LocationRewriteFilter locationRewriteFilter(
            ZuulProperties zuulProperties,
            RouteLocator routeLocator
    ) {
        return new LocationRewriteFilter(zuulProperties, routeLocator);
    }

//    @Bean
//    public SendErrorFilter sendErrorFilter() {
//        return new SendErrorFilter();
//    }

//    @Bean
//    public SendResponseFilter sendResponseFilter(ZuulProperties zuulProperties) {
//        return new SendResponseFilter(zuulProperties);
//    }

    @Bean
    public ZuulFilter filter(
            ZuulProperties zuulProperties,
            RouteLocator routeLocator
    ) {
        return new ZuulFilter() {
            @Override
            public String filterType() {
                return "post";
            }

            @Override
            public int filterOrder() {
                return 0;
            }

            @Override
            public boolean shouldFilter() {
                return true;
            }

            @Override
            public Object run() throws ZuulException {
                final RequestContext ctx = RequestContext.getCurrentContext();
                final HttpServletRequest request = ctx.getRequest();
                final UrlPathHelper urlPathHelper = new UrlPathHelper();
                final String pathWithinApplication = urlPathHelper.getPathWithinApplication(request);
                final Route route = routeLocator.getMatchingRoute(
                        pathWithinApplication
                );
                int statusCode = ctx.getResponseStatusCode();
                HttpStatus status = HttpStatus.valueOf(statusCode);
                log.debug("Route [ {} ] status [ {} ]", route, status);
                return null;
            }
        };
    }

//    @Bean
//    public DebugFilter debugFilter() {
//        return new DebugFilter();
//    }
//
//    @Bean
//    public ZuulFilter zuulFilter(){
//        return new ZuulFilter() {
//            @Override
//            public String filterType() {
//                return "post";
//            }
//
//            @Override
//            public int filterOrder() {
//                return 999999;
//            }
//
//            @Override
//            public boolean shouldFilter() {
//                return true;
//            }
//
//            @Override
//            public Object run() {
//                RequestContext ctx = RequestContext.getCurrentContext();
//                ctx.setDebugRouting(true);
//                ctx.setDebugRequest(true);
//                return null;
//            }
//        };
//    }
}
