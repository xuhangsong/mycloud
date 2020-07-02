package com.xhs.zookeeperclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class ZookeeperClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperClientApplication.class, args);
    }


    @Value("${server.port}")
    private String port;

    @RequestMapping("/client")
    public String client(@RequestParam(value="name",defaultValue = "xhs")String name, HttpServletRequest request){
        return "hello "+name +" by zookeeper , port="+request.getServerPort();
    }
    @RequestMapping("/serviceUrl")
    public String serviceUrl(@RequestParam(value="name",defaultValue = "xhs")String name){
        return serviceUrl();
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    public String serviceUrl() {
        List<ServiceInstance> list = discoveryClient.getInstances("zookeeper-client");
        if (list != null && list.size() > 0 ) {
           String str = "";
            for(ServiceInstance s:list){
              str+=s.getUri();
           }
           return str;
        }
        return null;
    }
}

