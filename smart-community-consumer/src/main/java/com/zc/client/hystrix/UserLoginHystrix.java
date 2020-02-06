package com.zc.client.hystrix;

import com.zc.client.UserLoginClient;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-02-04-21:28
 */
@Component
public class UserLoginHystrix implements UserLoginClient {

    @Override
    public Map<String, Object> loginByPhone(String phone, String password) {
        return null;
    }

    @Override
    public Map<String, Object> loginByLogin(String loginName, String password) {
        return null;
    }
}
