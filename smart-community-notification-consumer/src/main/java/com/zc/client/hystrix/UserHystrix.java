package com.zc.client.hystrix;

import com.zc.client.UserClient;
import com.zc.pojo.User;
import com.zc.vo.LayuiVO;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-02-04-21:28
 */
@Component
public class UserHystrix implements UserClient {


    @Override
    public Map<String, Object> loginByPhone(String phone, String password) {
        return null;
    }

    @Override
    public Map<String, Object> loginByLogin(String loginName, String password) {
        return null;
    }

    @Override
    public Map<String, Object> updatePassword(long userId, String password, String passwordNew) {
        return null;
    }

    @Override
    public Map<String, Object> putPassword(String phone, String password, String code) {
        return null;
    }

    @Override
    public Map<String, Object> addUser(User user) {
        return null;
    }

    @Override
    public Map<String, Object> setPassword(String phone, Long userId, Integer type, String password) {
        return null;
    }

    @Override
    public LayuiVO queryUserByHouseId(Long houseId) {
        return null;
    }

    @Override
    public LayuiVO queryUser(String userName, String phone, Integer state, Integer pageIndex, Integer pageSize) {
        return null;
    }

    @Override
    public Object stateUpdate(Long userId, Integer state) {
        return null;
    }
}
