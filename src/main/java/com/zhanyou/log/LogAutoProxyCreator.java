package com.zhanyou.log;

import org.aopalliance.intercept.MethodInterceptor;
import org.slf4j.LoggerFactory;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

public class LogAutoProxyCreator extends AbstractAutoProxyCreator {

    /**
     * 
     */
    private static final long serialVersionUID = 4121901959030601360L;

    private static org.slf4j.Logger log = LoggerFactory.getLogger(LogAutoProxyCreator.class);

    private static final MethodInterceptor methodInterceptor = new LoggerInterceptor();


    public LogAutoProxyCreator() {
        super();
        setProxyTargetClass(true);
    }



    private static boolean validateAttaLogger(Class<?> beanClass) {
        boolean isInterceptorNecessary = false;
        for (Method m : beanClass.getMethods()) {
            AttaLogger d = AnnotatedElementUtils.findMergedAnnotation(m, AttaLogger.class);
            if (d == null) {
                continue;
            }

            isInterceptorNecessary = true;

            if (StringUtils.isEmpty(d.name())) {
                throw new IllegalArgumentException("Logger.name is blank: " + m);
            }

            if (d.args().length > m.getParameterCount()) {
                throw new IllegalArgumentException("Logger.args.length > Method.parameterCount: " + m);
            }
        }

        return isInterceptorNecessary;
    }


    @Override
    protected Object[] getAdvicesAndAdvisorsForBean(Class<?> beanClass, String beanName, TargetSource targetSource) {

//注释掉如下代码，在其他地方需要打印日志时只要在函数上加上@AttaLogger，无需再在类上加上@LogAutoProxy注解
//        Annotation anno = beanClass.getAnnotation(LogAutoProxy.class);
//        if (anno == null) {
//            return null;
//        }

        boolean isLoggingNecessary = validateAttaLogger(beanClass);
        if (!isLoggingNecessary) {
            return null;
        }else {

            Object[] additional  = new Object[]{methodInterceptor};
            log.info("LogAutoProxy: {} {} with Logger", beanName, beanClass);

            return additional;
        }
    }
}
