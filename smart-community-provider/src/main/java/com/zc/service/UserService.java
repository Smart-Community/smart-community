package com.zc.service;

import com.zc.business.UserBusiness;
import com.zc.pojo.User;
import com.zc.util.CommonConstants;
import com.zc.util.MD5;
import com.zc.util.TokenUtil;
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
public class UserService {

    @Autowired
    private UserBusiness userBusiness;
    @Autowired
    private SmsService smsService;

    @ApiOperation(value = "使用手机号和密码登录")
    @PostMapping("/v1.0/user/loginByPhone")
    public Map<String, Object> loginByPhone(@RequestParam("phone") String phone,
                                            @RequestParam("password") String password) {
        User user = userBusiness.findByPhone(phone);
        return verifyLogin(user, password);
    }

    @ApiOperation(value = "使用登录名和密码登录")
    @PostMapping("/v1.0/user/login")
    public Map<String, Object> loginByLogin(@RequestParam("loginName") String loginName,
                                            @RequestParam("password") String password) {
        User user = userBusiness.findByLoginName(loginName);
        return verifyLogin(user, password);
    }

    @ApiOperation(value = "修改密码")
    @PostMapping("/v1.0/user/password/update")
    public Map<String, Object> updatePassword(@RequestParam("userId") long userId,
                                              @RequestParam("password") String password,
                                              @RequestParam("passwordNew") String passwordNew) {
        User user = userBusiness.findByUserId(userId);
        password = MD5.getMD5String(password);
        if (!password.equals(user.getUserPassword())) {
            return ResultWrap.init(CommonConstants.FALIED, "密码错误");
        }
        password = MD5.getMD5String(passwordNew);
        user.setUserPassword(password);
        return ResultWrap.init(CommonConstants.SUCCESS, "修改成功", userBusiness.save(user));
    }

    @ApiOperation(value = "设置密码")
    @PostMapping("/v1.0/user/password/put")
    public Map<String, Object> putPassword(@RequestParam("phone") String phone,
                                           @RequestParam("password") String password,
                                           @RequestParam("code") String code) {
        if (!smsService.verifySms(phone, code)) {
            return ResultWrap.init(CommonConstants.FALIED, "验证码错误");
        }
        User user = userBusiness.findByPhone(phone);
        user.setUserPassword(MD5.getMD5String(password));
        user = userBusiness.save(user);
        user.setUserPassword(null);
        return ResultWrap.init(CommonConstants.SUCCESS, "重置成功", user);
    }

    private Map<String, Object> verifyLogin(User user, String password) {
        if (user == null) {
            return ResultWrap.init(CommonConstants.FALIED, "用户不存在");
        }
        password = MD5.getMD5String(password);
        if (!password.equals(user.getUserPassword())) {
            return ResultWrap.init(CommonConstants.FALIED, "账户或密码错误");
        }
        String token = TokenUtil.createToken(user.getUserId(), user.getUserPhone());
        user.setToken(token).setUserPassword(null);
        return ResultWrap.init(CommonConstants.SUCCESS, "登录成功", user);
    }
}
