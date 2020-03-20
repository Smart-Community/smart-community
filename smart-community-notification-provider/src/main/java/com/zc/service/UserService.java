package com.zc.service;

import com.zc.business.HouseBusiness;
import com.zc.business.UserBusiness;
import com.zc.pojo.House;
import com.zc.pojo.User;
import com.zc.util.CommonConstants;
import com.zc.util.MD5;
import com.zc.util.TokenUtil;
import com.zc.vo.LayuiVO;
import com.zc.vo.ResultWrap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    private HouseBusiness houseBusiness;
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

    @ApiOperation("新增用户")
    @PostMapping("/v1.0/user/create")
    public Map<String, Object> addUser(@RequestBody User user) {
        user.setUserPassword(MD5.getMD5String("123456"));
        if (userBusiness.findByPhone(user.getUserPhone()) != null) {
            return ResultWrap.init(CommonConstants.FALIED, "手机号已存在", userBusiness.addUser(user));
        }
        return ResultWrap.init(CommonConstants.SUCCESS, "添加用户成功", userBusiness.addUser(user));
    }

    @PostMapping("/v1.0/user/state/update")
    public Object stateUpdate(@RequestParam("userId") Long userId,
                              @RequestParam("state") Integer state) {
        if (userId == 1L) {
            return ResultWrap.init(CommonConstants.FALIED, "不可禁用");
        }
        userBusiness.updateState(userId, state);
        return ResultWrap.init(CommonConstants.SUCCESS, "修改成功");
    }

    /**
     * type=1   根据userId设置   type=2 根据手机号设置
     *
     * @param phone
     * @param userId
     * @param type
     * @param password
     * @return
     */
    @ApiOperation("设置密码")
    @PostMapping("/v1.0/password/set")
    public Map<String, Object> setPassword(@RequestParam(value = "phone", required = false) String phone,
                                           @RequestParam(value = "userId", required = false) Long userId,
                                           @RequestParam("type") Integer type,
                                           @RequestParam("password") String password) {
        User user;
        if (type == 1) {
            user = userBusiness.findByUserId(userId);
        } else {
            user = userBusiness.findByPhone(phone);
        }
        user.setUserPassword(MD5.getMD5String(password));
        userBusiness.save(user);
        return ResultWrap.init(CommonConstants.SUCCESS, "修改成功");
    }

    @PostMapping("/v1.0/user/by/houseId/{houseId}")
    public LayuiVO queryUserByHouseId(@PathVariable("houseId") Long houseId) {
        LayuiVO layuiVO = new LayuiVO();
        layuiVO.setData(userBusiness.findByHouseId(houseId));
        layuiVO.setCount(userBusiness.countByHouseId(houseId));
        layuiVO.setCode(0);
        layuiVO.setMsg("查询成功");
        return layuiVO;
    }

    @RequestMapping("/v1.0/user/query/page")
    public LayuiVO queryUser(@RequestParam(value = "userName", required = false) String userName,
                             @RequestParam(value = "phone", required = false) String phone,
                             @RequestParam(value = "state", required = false) Integer state,
                             @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageIndex,
                             @RequestParam(value = "limit", required = false, defaultValue = "20") Integer pageSize) {

        return userBusiness.query(userName, phone, state, pageIndex, pageSize);
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
        User catUser = new User();
        BeanUtils.copyProperties(user, catUser);
        catUser.setUserPassword(null);
        catUser.setToken(token);
        user.setUserLastLoginTime(new Date());
        userBusiness.save(user);
        return ResultWrap.init(CommonConstants.SUCCESS, "登录成功", catUser);
    }
}
