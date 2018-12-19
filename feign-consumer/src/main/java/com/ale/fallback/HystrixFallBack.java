package com.ale.fallback;

import com.ale.intf.FeignService;
import org.springframework.stereotype.Service;

@Service
public class HystrixFallBack implements FeignService {

    @Override
    public String hi(String name) {
        return "fallback sorry " + name ;
    }
}
