package com.zc.controller;

import com.alibaba.fastjson.JSON;
import com.zc.client.UserLoginClient;
import com.zc.pojo.User;
import com.zc.util.CommonConstants;
import com.zc.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-02-04-19:04
 */
@Api(value = "用户登录相关接口")
@RestController
public class UserLoginController {

    @Value("${userinfo.key}")
    private String USER_INFO_KYE;

    @Autowired
    private UserLoginClient userLoginClient;

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "用户登录")
    @PostMapping("/public/user/login")
    public Map<String, Object> userLogin(@RequestParam("loginString") String loginString,
                                         @RequestParam("password") String password,
                                         @RequestParam("type") int type) {
        Map<String, Object> map;
        if (type == 1) {
            map = userLoginClient.loginByLogin(loginString, password);
        } else {
            map = userLoginClient.loginByPhone(loginString, password);
        }
        if (map.get(CommonConstants.RESP_CODE).equals(CommonConstants.SUCCESS)) {
            User user = (User) map.get(CommonConstants.RESULT);
            String userString = JSON.toJSONString(user);
            redisUtil.setStr(USER_INFO_KYE + user.getUserId(), userString, 30L);
        }
        return map;
    }

    @PostMapping("/private/user/login")
    public  void test(){
        System.out.println(2);
    }
}
