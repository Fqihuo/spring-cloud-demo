package com.fanghao.service;

import com.fanghao.util.R;
import org.springframework.stereotype.Component;

@Component
public class PowerfeignFallBack implements PowerFeignClient{

    @Override
    public Object getPower() {
        return R.error("power服务不可用");
    }

    @Override
    public Object getPowerList() {
        return R.error("getPowerList服务不可用");
    }
}
