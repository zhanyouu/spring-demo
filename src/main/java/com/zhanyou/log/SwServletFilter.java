package com.zhanyou.log;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class SwServletFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
        FilterChain filterChain) throws ServletException, IOException {
        String linkHeader = httpServletRequest.getHeader(UserConstants.LINK_HEADER);
        String uri = httpServletRequest.getRequestURI();
        try {
            circleCheck(linkHeader, uri);
            SwContext.setCurrentSw(buildLink(linkHeader));
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } finally {
            SwContext.clear();
        }
    }

    private String buildLink(String linkHeader) {
        String appName = getEnvironment().getProperty(UserConstants.APP_NAME);
        if(Strings.isNullOrEmpty(linkHeader)) return appName;
        if(linkHeader.endsWith(appName)) return linkHeader;

        return new StringBuilder().append(linkHeader).append(UserConstants.COMMO).append(appName).toString();
    }

    private void circleCheck(String linkHeader, String uri) {
        String appName = getEnvironment().getProperty(UserConstants.APP_NAME);
        try {
            if (!StringUtils.isEmpty(linkHeader) &&
                Splitter.on(UserConstants.COMMO).splitToList(linkHeader).contains(appName)) {
                log.warn("App call circle exist!,  {} -> {}, uri = {}", linkHeader, appName, uri);
            }
        } catch (Exception e) {
            log.error("FeignSwInterceptor circle check error", e);
        }
    }


}
