package com.fanghao.controller;

import org.apache.tomcat.jni.Time;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class PowerController1 {

    @RequestMapping("/getPower.do")
    public Object getPower() throws Exception{
        Map<String, String> map = new HashMap<>();
        map.put("key", "power1");
        Thread.sleep(1000*10);
        return map;
    }
}
