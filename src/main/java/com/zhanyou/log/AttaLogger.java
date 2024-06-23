
package com.zhanyou.log;

import java.lang.annotation.*;

/**
 *
 * AOP logger of Atta
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Inherited
public @interface AttaLogger {

    /**
     * Logger name
     */
    String name();


    /**
     * Arguments of elements to be logged.
     * 'param' is used as the default, reference to retrieve the context from input
     *
     * @return
     */
    String[] args() default {};

    /**
     * The array of the logging elements.
     * e.g. {"hi", "${param.name}"}
     *
     */
    String[] value();

}
