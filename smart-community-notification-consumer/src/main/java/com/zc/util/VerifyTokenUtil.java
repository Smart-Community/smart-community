package com.zc.util;

import com.alibaba.fastjson.JSONObject;
import com.zc.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author 小帅气
 * @create 2020-03-15-12:32
 */
@Component
public class VerifyTokenUtil {
    @Autowired
    private RedisUtil redisUtil;
    @Value("${permission.key}")
    private String PERMISSION_KEY;
    @Value("${userinfo.key}")
    private String USER_INFO_KYE;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public boolean verify(String token, String prefix) {
        if (token != null && !token.trim().equals("null") && !token.trim().equals("")) {
            try {
                long userId = TokenUtil.getUserId(token);
                String userString = redisUtil.getStr(USER_INFO_KYE + userId);
                if (userString == null) {
                    return false;
                }
                redisUtil.setStr(USER_INFO_KYE + userId, userString, 30L);
                User user = JSONObject.parseObject(userString, User.class);
                long roleId = user.getUserRoleId();
                String prefixString = (String) stringRedisTemplate.opsForHash().get(PERMISSION_KEY, roleId + "");
                if (prefixString.contains(prefix)) {
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
