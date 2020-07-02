package com.xhs.serviceribbon;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.beans.Transient;

/**
 * @author xuhan  build  2019/1/2
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hystrixError")

    public String clientService(String name){
//        return "ribbon rest " + restTemplate.getForObject("HTTP://SERVICE-CLIENT/client?name="+name,String.class);
        return "ribbon rest " + restTemplate.getForObject("HTTP://zookeeper-client/client?name="+name,String.class);
//        return "ribbon rest " + restTemplate.getForObject("HTTP://zookeeper-client/client?name="+name,String.class);
    }

    public String hystrixError(String name){
        return name+" ribbon restTemplate  error !";
    }
}
