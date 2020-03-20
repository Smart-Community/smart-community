package com.zc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zc.client.MenuClient;
import com.zc.client.UserClient;
import com.zc.pojo.Menu;
import com.zc.pojo.User;
import com.zc.util.CommonConstants;
import com.zc.util.RedisUtil;
import com.zc.util.TokenUtil;
import com.zc.util.VerifyTokenUtil;
import com.zc.vo.LayuiVO;
import com.zc.vo.ResultWrap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-02-04-19:04
 */
@Api(value = "用户登录相关接口")
@RestController
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Value("${userinfo.key}")
    private String USER_INFO_KYE;

    @Resource
    private UserClient userClient;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MenuClient menuClient;

    @Autowired
    private VerifyTokenUtil verifyTokenUtil;

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
        if (CommonConstants.SUCCESS.equals(map.get(CommonConstants.RESP_CODE))) {
            User user = null;
            String userJson = JSONObject.toJSONString(map.get(CommonConstants.RESULT));
            try {
                user = JSONObject.parseObject(userJson, User.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            redisUtil.setStr(USER_INFO_KYE + user.getUserId(), userJson, 30L);
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
    @PostMapping("/admin/user/add")
    public Object addUser(User user) {
        return userClient.addUser(user);
    }

    @GetMapping("/user/get")
    public Object getUserInfo(@RequestHeader("token") String token) {
        Long userId = null;
        try {
            userId = TokenUtil.getUserId(token);
        } catch (Exception e) {
            LOG.error("token解析错误");
            return ResultWrap.init(CommonConstants.ERROR_TOKEN, "token无效");
        }

        User user = getUser(userId);
        if (user == null) {
            return ResultWrap.init(CommonConstants.FALIED, "用户未登录");
        }
        return ResultWrap.init(CommonConstants.SUCCESS, "成功", user);
    }

    @ApiOperation("分页查询用户")
    @RequestMapping("/public/user/query")
    public LayuiVO queryUser(@RequestParam("token") String token,
                             @RequestParam(value = "userName", required = false) String userName,
                             @RequestParam(value = "phone", required = false) String phone,
                             @RequestParam(value = "state", required = false) Integer state,
                             @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageIndex,
                             @RequestParam(value = "limit", required = false, defaultValue = "20") Integer pageSize) {
        if (!verifyTokenUtil.verify(token, "admin")) {
            return null;
        }
        return userClient.queryUser(userName, phone, state, pageIndex, pageSize);
    }

    @ApiOperation("注销")
    @GetMapping("/user/cancel")
    public Object userCancel(@RequestHeader("token") String token) {
        Long userId = null;
        try {
            userId = TokenUtil.getUserId(token);
        } catch (Exception e) {
            LOG.error("token解析错误");
            return ResultWrap.init(CommonConstants.ERROR_TOKEN, "token无效");
        }
        redisUtil.del(USER_INFO_KYE + userId);
        return ResultWrap.init(CommonConstants.SUCCESS, "注销成功");
    }

    @ApiOperation("禁用或启用")
    @RequestMapping("/admin/user/state/update")
    public Object updateState(@RequestParam("userId") Long userId,
                              @RequestParam("state") Integer state) {
        userClient.stateUpdate(userId, state);
        return ResultWrap.init(CommonConstants.SUCCESS, "修改成功");
    }

    @ApiOperation("修改密码")
    @PostMapping("/user/password/update")
    public Object updatePwd(@RequestHeader("token") String token, @RequestParam("newPwd") String newPwd) {
        long userId;
        try {
            userId = TokenUtil.getUserId(token);
        } catch (Exception e) {
            LOG.error("token解析错误");
            return ResultWrap.init(CommonConstants.ERROR_TOKEN, "token无效");
        }
        return userClient.setPassword(null, userId, 1, newPwd);
    }


    @GetMapping("/public/menu/get")
    public Map<String, Object> getMenuList(@RequestParam("token") String token) {
        Long userId = null;
        try {
            userId = TokenUtil.getUserId(token);
        } catch (Exception e) {
            LOG.error("无效token");
            return ResultWrap.init(CommonConstants.FALIED, "非法访问");
        }
        User user = getUser(userId);
        if (user == null) {
            return ResultWrap.init(CommonConstants.FALIED, "请登录");
        }
        Map<String, Object> menuListByRoleId = menuClient.getMenuListByRoleId(user.getUserRoleId());
        List<LinkedHashMap<String, Object>> list = (List<LinkedHashMap<String, Object>>) menuListByRoleId.get("data");
        for (Map map : list) {
            map.put("href", map.get("href") + "?token=" + token);
        }
        return menuListByRoleId;
    }

    @GetMapping("/public/user/by/houseId/{houseId}")
    public LayuiVO byHouseId(@RequestParam("token") String token,
                             @PathVariable("houseId") Long houseId) {
        if (!verifyTokenUtil.verify(token, "admin")) {
            return null;
        }
        return userClient.queryUserByHouseId(houseId);

    }


    //    从缓存中获取user信息
    private User getUser(Long userId) {
        String userJson = redisUtil.getStr(USER_INFO_KYE + userId);
        if (StringUtils.isEmpty(userJson)) {
            return null;
        }
        return JSON.parseObject(userJson, User.class);
    }

}
