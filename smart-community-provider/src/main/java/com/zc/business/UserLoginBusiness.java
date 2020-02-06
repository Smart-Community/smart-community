package com.zc.business;

import com.zc.pojo.User;

/**
 * @author 小帅气
 * @create 2020-02-05-17:54
 */
public interface UserLoginBusiness {

    User loginByPhone(String phone);

    User loginByLoginName(String loginName);
}
