package com.zhanyou.log;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.util.HashMap;
import java.util.Map;


public class LoggerInterceptor implements MethodInterceptor {

    private static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LoggerInterceptor.class);

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        AttaLogger logger = AnnotatedElementUtils.findMergedAnnotation(methodInvocation.getMethod(), AttaLogger.class);
        if (logger == null) {
            return methodInvocation.proceed();
        }

        long beginTime = System.currentTimeMillis();
        Object ret = null;
        Throwable error = null;
        boolean async = false;

        try {
            ret = methodInvocation.proceed();
            async = registerHookIfNecessary(logger, beginTime, methodInvocation);

            return ret;
        } catch (Throwable e) {
            error = e;
            throw e;
        } finally {
            if(!async){
                long timeElapsed = System.currentTimeMillis() - beginTime;
                afterMethodExecution(logger, methodInvocation, ret, error, timeElapsed, 0);
            }
        }

    }

    private static boolean registerHookIfNecessary(AttaLogger logger, long beginTime, MethodInvocation methodInvocation){


        return false;
    }

    private static void afterMethodExecution(AttaLogger logger, MethodInvocation methodInvocation,
                                             Object ret, Throwable err, long timeElapsed1, long timeElapsed2){

        try {
            printLog(logger, methodInvocation, ret, err, timeElapsed1, timeElapsed2);
        } catch (Throwable e){
            LOGGER.error("Error happened when logging - " + e.getMessage(), e);
        }
    }



    private static void printLog(AttaLogger logger, MethodInvocation invocation,
                                 Object ret, Throwable error, long timeElapsed1, long timeElapsed2) throws Exception {

        Map<String, Object> context = buildContext(logger, invocation.getArguments());
        
        context.put("ret", ret);
        context.put("err", error);

        LogFormatter entry = AttaLoggerCache.INSTANCE.getLogger(logger);

        entry.log(context, error == null, timeElapsed1, timeElapsed2);

    }

    private static Map<String, Object> buildContext(AttaLogger logger, Object[] args) {
        Map<String, Object> ret = new HashMap<>();

        if (args == null || args.length == 0) {
            return ret;
        }

        if (logger.args() == null || logger.args().length == 0) {

            if (args.length == 1) {
                ret.put("param", args[0]);
                return ret;
            }

            ret.put("param", args);
            return ret;

        }

        for (int i = 0; i < logger.args().length && i < args.length; i++) {
            ret.put(logger.args()[i], args[i]);
        }

        return ret;
    }

}
