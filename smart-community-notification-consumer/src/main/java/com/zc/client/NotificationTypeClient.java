package com.zc.client;

import com.zc.client.hystrix.NotificationTypeHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-03-08-22:33
 */
@FeignClient(name = "provider", fallbackFactory = NotificationTypeHystrix.class)
public interface NotificationTypeClient {

    @GetMapping("/v1.0/notification/type/get")
    public Map<String, Object> getNotificationType();
}
