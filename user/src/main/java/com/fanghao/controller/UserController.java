package com.fanghao.controller;

import com.fanghao.service.PowerFeignClient;
import com.fanghao.util.R;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    RestTemplate restTemplate;

    private static final String  POWER_URL="http://SERVER-POWER";
    private static final String  ORDER_URL="http://SERVER-ORDER";

    @Autowired
    private PowerFeignClient powerFeignClient;

    @RequestMapping("/getUser.do")
    public R getUser(){
        Map<String,Object> map = new HashMap<>();
        map.put("key","user");
        return R.success("返回成功",map);
    }

    @RequestMapping("getOrder.do")
    public R getOrder(){
        return R.success("操作成功",restTemplate.getForObject(ORDER_URL+"/getOrder.do",Object.class));
    }

    @RequestMapping("/getFeignPower.do")
    public R getFeignPower(){
        return R.success("操作成功",powerFeignClient.getPower());
    }

    @RequestMapping("/getPower.do")
    @HystrixCommand(threadPoolKey = "getpower",
        threadPoolProperties = {@HystrixProperty(name = "coreSize", value = "1")
        },fallbackMethod = "getFeignPowerFallback")
    public R getPower(){

        return R.success("操作成功",restTemplate.getForObject(POWER_URL+"/getPower.do",Object.class));
    }

    public R getFeignPowerFallback(){
        return R.error("系统维护中");
    }
}
