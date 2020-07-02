package com.xhs.servicefeign;

import com.xhs.servicefeign.myfeign.EnableRestClient;
import com.xhs.servicefeign.myfeign.SchedulRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(clients = SchedulService.class)
@RestController
@EnableRestClient(clients = SchedulRestService.class)
public class ServiceFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFeignApplication.class, args);
    }

    @Autowired
    SchedulService schedulService;

    @Autowired
    SchedulRestService schedulRestService;

    @Bean
    @LoadBalanced
    public RestTemplate loadBalancedRestTemplate(){return new RestTemplate();}

    @RequestMapping("/feign")
    public String hello(@RequestParam(value="name",defaultValue = "xhs")String name){
        return "feign "+ schedulService.sayHelloFromClientOne(name);
    }
    @RequestMapping("/rest")
    public String rest(@RequestParam(value="name",defaultValue = "xhs")String name){
        return "feign "+ schedulRestService.sayHelloFromClientOne(name);
    }
}

