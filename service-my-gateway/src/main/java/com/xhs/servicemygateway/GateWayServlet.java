package com.xhs.servicemygateway;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * @author xuhan  build  2019/1/15
 */

@WebServlet(name = "gateway",urlPatterns = "/gateway")
public class GateWayServlet extends HttpServlet {

    @Autowired
    private DiscoveryClient discoveryClient;

    private ServiceInstance randomChooseServiceInstance(String serviceName){
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
        int size = serviceInstances.size();
        return serviceInstances.get(new Random(size).nextInt());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.service(req, resp);
        String pathInfo =  req.getPathInfo();
        String[] paths = StringUtils.split(pathInfo.substring(1),"/");
        String serviceName = paths[0];
        String serviceUri = "/" +paths[1];

        ServiceInstance serviceInstance = randomChooseServiceInstance(serviceName);
        //  拼接目标地址
        String serviceUrl = buildServiceUrl(serviceInstance,serviceUri);


    }

    private String buildServiceUrl(ServiceInstance serviceInstance, String serviceUri) {
        StringBuilder sb = new StringBuilder();
        sb.append(serviceInstance.isSecure()?"https://":"http://")
                .append(serviceInstance.getHost())
                .append(":")
                .append(serviceInstance.getPort())
                .append(serviceUri);

        return sb.toString();
    }

}
