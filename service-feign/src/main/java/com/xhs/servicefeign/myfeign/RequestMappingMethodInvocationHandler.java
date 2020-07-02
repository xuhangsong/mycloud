package com.xhs.servicefeign.myfeign;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

/**
 * @author xuhan  build  2019/1/9
 */
public class RequestMappingMethodInvocationHandler implements InvocationHandler {

    private final String serverName;
    private final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
    private final BeanFactory beanFactory;

    public RequestMappingMethodInvocationHandler(String serverName, BeanFactory beanFactory) {
        this.serverName = serverName;
        this.beanFactory = beanFactory;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RequestMapping requestMapping = findAnnotation(method,RequestMapping.class);
        if(requestMapping!=null){
            String[] uri = requestMapping.value();


            StringBuilder urlBuilder = new StringBuilder("http://").append(serverName).append("/").append(uri[0]).append("?");

            //获取参数个数
            int count = method.getParameterCount();

            //得到参数名和参数类型、注解类型
            String[] paramNames = parameterNameDiscoverer.getParameterNames(method);
            Class<?>[] paramTypes = method.getParameterTypes();
            Annotation[][] annotations = method.getParameterAnnotations();

            StringBuilder queryStringBuilder = new StringBuilder();

            for(int i=0;i<count;i++){
                Annotation[] paramAnnotations = annotations[i];
                Class<?> paramType = paramTypes[i];
//                RequestParam requestParam = method.getAnnotation(RequestParam.class);
                RequestParam requestParam = (RequestParam) paramAnnotations[0];
                if(requestParam!=null){
//                    String paramName = paramNames[i];
                    //FIXED
                    String paramName = "";
                    String requestParamName = StringUtils.hasText(requestParam.value())?requestParam.value():paramName;
                    String requestParamValue = String.class.equals(paramType)?(String)args[i]:String.valueOf(args[i]);

                    queryStringBuilder.append("&")
                            .append(requestParamName).append("=").append(requestParamValue);
                }
            }
            String url = urlBuilder.append(queryStringBuilder).toString();

            RestTemplate restTemplate = beanFactory.getBean("loadBalancedRestTemplate",RestTemplate.class);

            return restTemplate.getForObject(url,method.getReturnType());
        }
        return null;
    }
}
