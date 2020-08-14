package com.fanghao.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PowerController {

    @RequestMapping("/getPower.do")
    public Object getPower(){
        Map<String, String> map = new HashMap<>();
        map.put("key", "power");
        return map;
    }
}
