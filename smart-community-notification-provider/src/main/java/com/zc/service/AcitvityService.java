package com.zc.service;

import com.zc.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-02-17-15:29
 */
@RestController
public class AcitvityService {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/v1.0/activity/create")
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> createNewActivity() {

        return null;
    }

    @Transactional
    @RequestMapping("/v1.0/activity/update")
    public Map<String,Object> updateAcitvity(){

        return null;
    }



}
