package com.xhs.servicefeign.myfeign;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author xuhan  build  2019/1/7
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(RestClientsRegistrar.class)
public @interface EnableRestClient {

    /**
     * 指定restClient
     * @return
     */
    Class<?>[] clients() default {};
}
