package com.xhs.servicemygateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@ServletComponentScan("")
public class ServiceMyGatewayApplication {


    public static void main(String[] args) {
        SpringApplication.run(ServiceMyGatewayApplication.class, args);
    }

}

