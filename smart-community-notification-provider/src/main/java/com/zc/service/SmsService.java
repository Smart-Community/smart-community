package com.zc.service;

import com.zc.util.*;
import com.zc.vo.ResultWrap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 小帅气
 * @create 2020-02-06-20:51
 */
@Api(value = "短信通知相关接口")
@RestController
public class SmsService {

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final static String PHONE_REGEX = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))" +
            "\\d{8}$";
    @Autowired
    private RedisUtil redisUtil;
    private Random random;

    @ApiOperation("向指定手机发送验证码")
    @RequestMapping("/v1.0/sms/send")
    public Object sendSms(@RequestParam("phone") String phone) {
        if (phone.length() != 11) {
            return ResultWrap.init(CommonConstants.FALIED, "手机号应为11位");
        }
        Pattern p = Pattern.compile(PHONE_REGEX);
        Matcher m = p.matcher(phone);
        if (!m.matches()) {
            return ResultWrap.init(CommonConstants.FALIED, "请输入正确的手机号");
        }
        ArrayList<String> list = new ArrayList<>();
        String code = random.nextInt(999999) % 900000 + 100000 + "";

        redisUtil.setStr(phone, code, 5L);
        try {

        } catch (Exception e) {
            LOG.error("=======发送短信失败=======");
            redisUtil.del(phone);
            return ResultWrap.init(CommonConstants.FALIED, "发送失败");
        }
        return ResultWrap.init(CommonConstants.SUCCESS, "发送成功");
    }


    /**
     * 校验短信验证码
     *
     * @param phone      手机号
     * @param verifyCode 验证码
     * @return
     */
    public boolean verifySms(String phone, String verifyCode) {
        String code = redisUtil.getStr(phone);
        if (verifyCode.equals(code)) {
            redisUtil.del(phone);
            return true;
        }
        return false;
    }
}
