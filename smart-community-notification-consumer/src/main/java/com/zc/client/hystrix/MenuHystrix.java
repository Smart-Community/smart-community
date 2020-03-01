package com.zc.client.hystrix;

import com.zc.client.MenuClient;

import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-02-29-20:19
 */
public class MenuHystrix implements MenuClient {
    @Override
    public Map<String, Object> getMenuListByRoleId(Long roleId) {
        return null;
    }
}
