package com.ale.intf;

import com.ale.fallback.HystrixFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-hello",fallback = HystrixFallBack.class)
public interface FeignService {
    @RequestMapping(value = "/hi",method= RequestMethod.GET)
    String hi(@RequestParam(value ="name",defaultValue = "张三")String name);
}
