package com.ale.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Value("${server.port}")
    String port;

    @RequestMapping("/hello")
    public String hello() {
        System.out.println("this is eureka-client, port:" + port);
        return "this is eureka-client, port:" + port;
    }

    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        System.out.println("EurekaClientApplication hi!!!" + name);
        return "this is eureka-client! hi " + name + " ,i am from port:" + port;
    }
}
