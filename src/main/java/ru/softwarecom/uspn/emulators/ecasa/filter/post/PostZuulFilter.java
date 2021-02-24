package ru.softwarecom.uspn.emulators.ecasa.filter.post;

import com.netflix.zuul.ZuulFilter;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;

public abstract class PostZuulFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return POST_TYPE;
    }
}
