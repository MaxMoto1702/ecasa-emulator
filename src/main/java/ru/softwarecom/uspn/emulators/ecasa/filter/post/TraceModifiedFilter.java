package ru.softwarecom.uspn.emulators.ecasa.filter.post;

import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static ru.softwarecom.uspn.emulators.ecasa.filter.support.FilterConstants.TRACE_MODIFIED_FILTER_ORDER;

@Slf4j
@Component
public class TraceModifiedFilter extends TraceFilter {
    @Override
    public int filterOrder() {
        return TRACE_MODIFIED_FILTER_ORDER;
    }

    @Override
    public Object run() throws ZuulException {
        return super.run();
    }
}
