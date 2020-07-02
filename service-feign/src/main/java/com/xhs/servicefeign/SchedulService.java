package com.xhs.servicefeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xuhan  build  2019/1/2
 */
@FeignClient(value = "zookeeper-client",fallback = SchedulServiceHystrix.class)
public interface SchedulService {
    @RequestMapping("/client")
    String sayHelloFromClientOne(@RequestParam(value="name",defaultValue = "xhs") String name);
}
