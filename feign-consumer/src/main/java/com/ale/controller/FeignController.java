package com.ale.controller;

import com.ale.intf.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    @Autowired
    FeignService feignService;

    @GetMapping("/hi")
    public String sayHi(String name){
        System.out.print( "FeignController --> hi() " + name + "!!!!!");
        return feignService.hi(name);
    }
}
