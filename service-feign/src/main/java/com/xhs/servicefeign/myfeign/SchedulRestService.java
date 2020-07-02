package com.xhs.servicefeign.myfeign;

import com.xhs.servicefeign.ServiceFeignApplication;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author xuhan  build  2019/1/2
 */
@RestClient(name = "zookeeper-client")
public interface SchedulRestService {
    @RequestMapping("/client")
    String sayHelloFromClientOne(@RequestParam(value = "name", defaultValue = "xhs") String name);


    public static void main(String[] args) {
//        Method method = ReflectionUtils.findMethod(ServiceFeignApplication.class,"hello",String.class);
//        System.out.println(Arrays.toString(method.getParameters()));
//        ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
//        String[] paramNames = parameterNameDiscoverer.getParameterNames(method);
//        System.out.println(Arrays.toString(paramNames));


        String[] ss = null;
        ss = "".split(",");
        for(String s:ss){

        }
        String str = "12345678";
        String device = "1234567A";
        System.out.println(str.charAt(str.length()-1));
        char lastChar = device.charAt(device.length()-1);
        System.out.println(lastChar=='A');
    }
}
