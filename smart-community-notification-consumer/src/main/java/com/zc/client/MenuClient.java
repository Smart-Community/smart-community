package com.zc.client;

import com.zc.client.hystrix.MenuHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-02-29-20:18
 */
@FeignClient(name = "provider", fallback = MenuHystrix.class)
public interface MenuClient {
    @RequestMapping("/v1.0/menu/menulist/get")
    public Map<String, Object> getMenuListByRoleId(@RequestParam("roleId") Long roleId);
}
