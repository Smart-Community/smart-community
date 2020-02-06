package com.zc.business.impl;

import com.zc.business.UserLoginBusiness;
import com.zc.pojo.User;
import com.zc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author 小帅气
 * @create 2020-02-05-17:54
 */
@Service
@CacheConfig(cacheNames = {"userLoginBusiness"})
public class UserLoginBusinessImpl implements UserLoginBusiness {

    @Autowired
    private UserRepository userRepository;


    @Override
    @Cacheable(key = "#p0-#p2")
    public User loginByPhone(String phone) {
        return userRepository.findByUserPhone(phone);
    }

    @Override
    @Cacheable(key = "#p0-#p2")
    public User loginByLoginName(String loginName) {
        return userRepository.findByUserLogin(loginName);
    }
}
