package com.zc.controller;

import com.zc.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 小帅气
 * @create 2020-02-17-15:24
 */
@RestController
public class AcitvityController {

    @Autowired
    private RedisUtil redisUtil;


}
