package com.zc.client;

import com.zc.client.hystrix.FaultHystrix;
import com.zc.client.hystrix.HouseHystrix;
import com.zc.vo.LayuiVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-03-20-22:57
 */
@FeignClient(name = "provider", fallbackFactory = FaultHystrix.class)
public interface FaultClient {

    @PostMapping("/v1.0/fault/add")
    public Map<String ,Object> addFault(@RequestParam("userId")Long userId,
                                        @RequestParam("type")Integer type,
                                        @RequestParam("desc")String desc,
                                        @RequestParam("addr")String addr,
                                        @RequestParam("phone")String phone);

    @RequestMapping("/v1.0/fault/history/query/{userId}")
    public LayuiVO queryFaultHistory(@PathVariable("userId") Long userId,
                                     @RequestParam(value = "type", required = false) Integer type,
                                     @RequestParam(value = "state", required = false) Integer state,
                                     @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageIndex,
                                     @RequestParam(value = "limit", required = false, defaultValue = "20") Integer pageSize) ;

    }
