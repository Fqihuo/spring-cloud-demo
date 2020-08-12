package com.fanghao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class UserController {

    @Autowired
    RestTemplate restTemplate;
}
