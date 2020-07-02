package com.xhs.servicefeign.myfeign;

import java.lang.annotation.*;

/**
 * @author xuhan  build  2019/1/7
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestClient {

    String name();

}
