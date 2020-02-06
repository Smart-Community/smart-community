package com.zc.controller;

import com.alibaba.fastjson.JSON;
import com.zc.client.UserClient;
import com.zc.pojo.User;
import com.zc.util.CommonConstants;
import com.zc.util.RedisUtil;
import com.zc.util.TokenUtil;
import com.zc.vo.ResultWrap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-02-04-19:04
 */
@Api(value = "用户登录相关接口")
@RestController
public class UserLoginController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Value("${userinfo.key}")
    private String USER_INFO_KYE;

    @Autowired
    private UserClient userClient;

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "用户登录")
    @PostMapping("/public/user/login")
    public Map<String, Object> userLogin(@RequestParam("loginString") String loginString,
                                         @RequestParam("password") String password,
                                         @RequestParam("type") int type) {
        Map<String, Object> map;
        if (type == 1) {
            map = userClient.loginByLogin(loginString, password);
        } else {
            map = userClient.loginByPhone(loginString, password);
        }
        if (map.get(CommonConstants.RESP_CODE).equals(CommonConstants.SUCCESS)) {
            User user = (User) map.get(CommonConstants.RESULT);
            String userString = JSON.toJSONString(user);
            redisUtil.setStr(USER_INFO_KYE + user.getUserId(), userString, 30L);
        }
        return map;
    }

    @ApiOperation(value = "修改密码")
    @PostMapping("/user/user/password/update")
    public Object updatePassword(@RequestHeader("token") String token, @RequestParam("password") String password,
                                 @RequestParam("passwordNew") String passwordNew) {
        long userId;
        try {
            userId = TokenUtil.getUserId(token);
        } catch (Exception e) {
            LOG.error("token无效");
            return ResultWrap.init(CommonConstants.ERROR_TOKEN, "token无效");
        }
        return userClient.updatePassword(userId, password, passwordNew);
    }

    @ApiOperation(value = "忘记密码")
    @PostMapping("/public/user/password/forget")
    public Object forgetPassword(@RequestParam("phone") String phone, @RequestParam("password") String password,
                                 @RequestParam("code") String code) {
        return userClient.putPassword(phone, password, code);
    }

    @ApiOperation("新增用户")
    @PostMapping("/public/user/add")
    public Object addUser(User user, @RequestParam("tung_id") int tung_id, @RequestParam("unit_id") int unit_id,
                          @RequestParam("number") int number) {
        return userClient
    }
}
