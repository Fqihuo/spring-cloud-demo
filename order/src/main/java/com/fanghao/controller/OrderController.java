package com.fanghao.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {

    @RequestMapping("/getOrder.do")
    public Object getOrder(){
        Map<String,Object> map = new HashMap<>();
        map.put("key", "order");
        return map;
    }
}
