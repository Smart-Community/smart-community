package com.zc.client;

import com.zc.client.hystrix.NotificationHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-03-06-9:30
 */
@FeignClient(name = "provider", fallbackFactory = NotificationHystrix.class)
public interface NotificationClient {

    @PostMapping("/v1.0/notification/add")
    public Map<String, Object> addNotification(@RequestParam("adminId") long adminId,
                                               @RequestParam("typeId") int typeId,
                                               @RequestParam("top") String top,
                                               @RequestParam("desc") String desc);

    @PostMapping("/v1.0/notification/query")
    public Map<String, Object> queryNotification(@RequestParam(value = "pageSize", required = false, defaultValue =
            "20") Integer pageSize,
                                                 @RequestParam(value = "pageIndex", required = false, defaultValue =
                                                         "0") Integer pageIndex,
                                                 @RequestParam(value = "type", required = false) Integer type,
                                                 @RequestParam(value = "state", required = false) Integer state,
                                                 @RequestParam(value = "startTime", required = false) Long startTime,
                                                 @RequestParam(value = "endTime", required = false) Long endTime);

    @PostMapping("/v1.0/notification/state/update")
    public Map<String, Object> updateState(@RequestParam("id") Long id,
                                           @RequestParam("state") Integer state);
}
