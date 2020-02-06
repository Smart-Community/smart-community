package com.zc.business;

import com.zc.pojo.User;

/**
 * @author 小帅气
 * @create 2020-02-05-17:54
 */
public interface UserBusiness {

    User findByPhone(String phone);

    User findByLoginName(String loginName);

    User findByUserId(long userId);

    User save(User user);
}
