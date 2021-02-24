package ru.softwarecom.uspn.emulators.ecasa.filter.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RequiredArgsConstructor
@Slf4j
@Component
public class EcasaHeadersFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
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
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        HttpSession v = request.getSession();
        log.debug("Session {}", v);

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        log.debug("Auth {}", a);

        EcasaUser user = a != null && a.getPrincipal() instanceof EcasaUser ? (EcasaUser) a.getPrincipal() : null;

        if (user != null) {
            ctx.addZuulRequestHeader("OAM_REMOTE_USER", user.getUsername());
            ctx.addZuulRequestHeader("OAM_REMOTE_USER_DN", user.getUsername());
            ctx.addZuulRequestHeader("OAM_REMOTE_USER_Name", encode(user.getFirstName()));
            ctx.addZuulRequestHeader("OAM_REMOTE_USER_Surname", encode(user.getLastName()));
            ctx.addZuulRequestHeader("OAM_REMOTE_USER_PATRONUMIC", encode(user.getMiddleName()));
        }
        String cookie = "ObSSOCookie=" + v.getId();
        ctx.addZuulRequestHeader("Cookie", request.getHeader("Cookie") != null && !request.getHeader("Cookie").equals("") ?
                request.getHeader("Cookie") + "; " + cookie : cookie);

        return null;
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    private String encode(String value) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        byte[] encodeBytes = Base64.getEncoder().encode(bytes);
        String encoded = new String(encodeBytes);
        String transfer = "UTF-8     " + encoded;
        return transfer;
    }
}
