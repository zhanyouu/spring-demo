package com.zhanyou.log;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AttaApiInterceptor extends HandlerInterceptorAdapter {

    private static final String REQUEST_TIME_ATTR_NAME = "REQUEST_TIME";

    private static final String REQUEST_ID_HEADER_NAME = "X-Request-Id";

    private static final String REQUEST_DEVICE_ID_HEADER_NAME = "X-User-Agent-Context";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            response.setHeader(REQUEST_ID_HEADER_NAME, request.getHeader(REQUEST_ID_HEADER_NAME));
            request.setAttribute(REQUEST_TIME_ATTR_NAME, System.currentTimeMillis());
        } catch (Exception e) {
            log.error("AttaApiInterceptor preHandle error", e);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        try {
            log.info("api logger entity is: {}", composeLoggerEntity(request, response));
        } catch (Exception e) {
            log.error("AttaApiInterceptor postHandle error", e);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex != null) {
            log.error("api logger entity is: {}", composeLoggerEntity(request, response), ex);
        }
    }

    private LoggerEntity composeLoggerEntity(HttpServletRequest request, HttpServletResponse response) {
        long requestTime = (long) request.getAttribute(REQUEST_TIME_ATTR_NAME);
        long returnTime = System.currentTimeMillis();
        String reactTime = (returnTime - requestTime) + "ms";
        return LoggerEntity.builder()
                .userId((String) request.getAttribute("userId"))
                .firmId((String) request.getAttribute("firmId"))
                .uri(request.getRequestURI())
                .clientIp(getIp(request))
                .method(request.getMethod())
                .requestTime(requestTime)
                .returnTime(returnTime)
                .reactTime(reactTime)
                .httpStatusCode(response.getStatus() + "")
                .xRequestId(request.getHeader(REQUEST_ID_HEADER_NAME))
                .xDeviceId(request.getHeader(REQUEST_DEVICE_ID_HEADER_NAME))
                .userAgent(request.getHeader(HttpHeaders.USER_AGENT))
                .referer(request.getHeader(HttpHeaders.REFERER))
                .build();
    }


    private String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    @Data
    @Builder
    private static class LoggerEntity {
        private String userId;
        private String firmId;
        /**
         * 客户端请求ip
         */
        private String clientIp;
        /**
         * 客户端请求路径
         */
        private String uri;
        /**
         * 请求方式method,post,get等
         */
        private String method;
        /**
         * 请求时间
         */
        private Long requestTime;
        /**
         * 接口返回时间
         */
        private Long returnTime;
        /**
         * 接口请求用时
         */
        private String reactTime;
        /**
         * 请求时httpStatusCode代码，如：200,400,404等
         */
        private String httpStatusCode;
        /**
         * 用于前端异步跟踪
         */
        private String xRequestId;
        /**
         * TrackId
         */
        private String xTrackId;
        /**
         * 设备Id
         */
        private String xDeviceId;
        /**
         * UserAgent Header
         */
        private String userAgent;

        /**
         * Referer Header
         */
        private String referer;
    }

}
