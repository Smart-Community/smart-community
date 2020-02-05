package com.zc.service;

import com.zc.mapper.PermissionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小帅气
 * @create 2020-02-04-21:21
 */
@RestController
public class UserLoginService {
    @Resource
    private PermissionsMapper permissionsMapper;
    @RequestMapping("/test")
    public void test(){
        System.out.println(permissionsMapper.permissionsAll());
    }
}
