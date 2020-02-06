package com.zc.client;

import com.zc.client.hystrix.UserLoginHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-02-04-21:23
 */
@FeignClient(name = "prodiver", fallback = UserLoginHystrix.class)
public interface UserLoginClient {
    @PostMapping("/v1.0/user/loginByPhone")
    public Map<String, Object> loginByPhone(@RequestParam("phone") String phone,
                                            @RequestParam("password") String password);

    @PostMapping("/v1.0/user/login")
    public Map<String, Object> loginByLogin(@RequestParam("loginName") String loginName,
                                            @RequestParam("password") String password);
}
