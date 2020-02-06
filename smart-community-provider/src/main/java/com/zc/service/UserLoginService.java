package com.zc.service;

import com.zc.business.UserLoginBusiness;
import com.zc.pojo.User;
import com.zc.util.*;
import com.zc.vo.ResultWrap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-02-04-21:21
 */
@Api(value = "用户登录相关接口")
@RestController
public class UserLoginService {

    @Autowired
    private UserLoginBusiness userLoginBusiness;

    @ApiOperation(value = "使用手机号和密码登录")
    @PostMapping("/v1.0/user/loginByPhone")
    public Map<String, Object> loginByPhone(@RequestParam("phone") String phone,
                                            @RequestParam("password") String password) {
        User user = userLoginBusiness.loginByPhone(phone);
        return verifyLogin(user, password);
    }

    @ApiOperation(value = "使用登录名和密码登录")
    @PostMapping("/v1.0/user/login")
    public Map<String, Object> loginByLogin(@RequestParam("loginName") String loginName,
                                            @RequestParam("password") String password) {
        User user = userLoginBusiness.loginByPhone(loginName);
        return verifyLogin(user, password);
    }


    private Map<String, Object> verifyLogin(User user, String password) {
        if (user == null) {
            return ResultWrap.init(CommonConstants.FALIED, "用户不存在");
        }
        password = MD5.getMd5(password, 32);
        if (!password.equals(user.getUserPassword())) {
            return ResultWrap.init(CommonConstants.FALIED, "账户或密码错误");
        }
        String token = TokenUtil.createToken(user.getUserId(), user.getUserPhone());
        user.setToken(token).setUserPassword(null);
        return ResultWrap.init(CommonConstants.SUCCESS, "登录成功", user);
    }
}
