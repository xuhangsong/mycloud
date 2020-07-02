package com.xhs.servicegateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ServiceGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceGatewayApplication.class, args);
    }


    @Bean
    public RouteLocator myRoute(RouteLocatorBuilder builder){

        return builder.routes()
                .route(p-> p.path("/feign")
                        .uri("http://localhost:8765"))
//                .route(h->h
//                        .host("*.hystrix.com")
//                                    .filters(f -> f
//                                            .hystrix(config -> config
//                                                    .setName("mycmd")
//                                                    .setFallbackUri("forward:http:/fallback")))
//                                    .uri("http://localhost:8764/ribbon"))
                .build();

    }
    @RequestMapping("/fallback")
    public String fallback(){
        return "gateway fallback";
    }

}

