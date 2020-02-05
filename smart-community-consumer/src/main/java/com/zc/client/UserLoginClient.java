package com.zc.client;

import com.zc.client.hystrix.UserLoginHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 小帅气
 * @create 2020-02-04-21:23
 */
@FeignClient(name = "prodiver",fallback = UserLoginHystrix.class)
public interface UserLoginClient {

}
