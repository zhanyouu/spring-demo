//package com.zhanyou.log.interceptor;
//
//import com.google.common.base.Strings;
//import com.zhanyou.log.SwContext;
//import com.zhanyou.log.UserConstants;
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//
//public class FeignSwInterceptor implements RequestInterceptor {
//
//    private static Logger log = LoggerFactory.getLogger(FeignSwInterceptor.class);
//
//    @Value("${spring.application.name:unknow}")
//    private String appId;
//
//    @Override
//    public void apply(RequestTemplate template) {
//        String link = SwContext.getCurrentSw();
//        if(Strings.isNullOrEmpty(link)) {
//            link = appId;
//        }
//        template.header(UserConstants.LINK_HEADER, link);
//    }
//
//}
