package com.xhs.servicefeign;

import org.springframework.stereotype.Component;

/**
 * @author xuhan  build  2019/1/2
 */

@Component
public class SchedulServiceHystrix implements SchedulService{


    @Override
    public String sayHelloFromClientOne(String name) {
        return name+" feign  error !";
    }
}
