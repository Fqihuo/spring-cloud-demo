package com.fanghao.controller;

import com.fanghao.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class UserController {

    @Autowired
    RestTemplate restTemplate;

    private static final String  POWER_URL="http://SERVER-POWER";
    private static final String  ORDER_URL="http://SERVER-ORDER";

    @RequestMapping("/getUser.do")
    public R getUser(){
        Map<String,Object> map = new HashMap<>();
        map.put("key","user");
        return R.success("返回成功",map);
    }

    @RequestMapping("/getOrder.do")
    public R getOrder(){
        return R.success("操作成功",restTemplate.getForObject(ORDER_URL+"/getOrder.do",Object.class));
    }
}
