package com.zc.client;

import com.zc.client.hystrix.HouseHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-03-29-10:02
 */
@FeignClient(name = "provider", fallbackFactory = HouseHystrix.class)
public interface FaultTypeClient {

    @RequestMapping("/v1.0/fault/type/query")
    public Map<String, Object> queryFaultType();

    @RequestMapping("/v1.0/fault/type/add")
    public Map<String, Object> addFaultType(@RequestParam("name") String name);
}
