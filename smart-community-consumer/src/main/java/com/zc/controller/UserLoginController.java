package com.zc.controller;

import com.zc.client.UserLoginClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-02-04-19:04
 */
@RestController

public class UserLoginController {
    @Autowired
    private UserLoginClient userLoginClient;

    @RequestMapping("/public/user/login")
    public Map<String, Object> userLogin() {

        return null;
    }

}
