package com.zc.client;

import com.zc.client.hystrix.FaultHystrix;
import com.zc.vo.LayuiVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-04-01-21:57
 */
@FeignClient(name = "provider", fallbackFactory = FaultHystrix.class)
public interface ActivityClient {

    @RequestMapping("/v1.0/activity/create")
    public Map<String, Object> createNewActivity(@RequestParam("releaseId") Long releaseId,
                                                 @RequestParam("name") String name,
                                                 @RequestParam("desc") String desc,
                                                 @RequestParam("number") Integer number,
                                                 @RequestParam("limit") short limit,
                                                 @RequestParam("startTime") Date startTime,
                                                 @RequestParam("joinTime") Date joinTime);


    @PostMapping("/v1.0/activity/update")
    public Map<String, Object> updateAcitvity(@RequestParam("id") Long id);


    @PostMapping("/v1.0/activity/query/page")
    public LayuiVO queryActivy(@RequestParam("name") String name,
                               @RequestParam("status") Integer status,
                               @RequestParam("pageIndex") Integer pageIndex,
                               @RequestParam("pageSize") Integer pageSize);

}
