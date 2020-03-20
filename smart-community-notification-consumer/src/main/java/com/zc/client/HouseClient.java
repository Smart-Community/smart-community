package com.zc.client;

import com.zc.client.hystrix.HouseHystrix;
import com.zc.vo.LayuiVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-03-12-21:42
 */
@FeignClient(name = "provider", fallbackFactory = HouseHystrix.class)
public interface HouseClient {

    @PostMapping("/v1.0/house/query/page")
    public LayuiVO queryHouseInfo(@RequestParam(value = "tungId", required = false) Integer tungId,
                                  @RequestParam(value = "unitId", required = false) Integer unitId,
                                  @RequestParam(value = "number", required = false) Integer number,
                                  @RequestParam(value = "maxArea", required = false) BigDecimal maxArea,
                                  @RequestParam(value = "minArea", required = false) BigDecimal minArea,
                                  @RequestParam(value = "state", required = false) Integer state,
                                  @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageIndex,
                                  @RequestParam(value = "limit", required = false, defaultValue = "20") Integer pageSize);

    @PostMapping("/v1.0/house/query/by/{userId}")
    public LayuiVO queryHouseByUserId(@PathVariable("userId") Long userId,
                                      @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
                                      @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize);

    @PostMapping("/v1.0/house/pay/managent/{houseId}")
    public Map<String,Object> payManagent(@PathVariable("houseId")Long houseId,
                                          @RequestParam("adminId")Long adminId,
                                          @RequestParam("money")BigDecimal money);
}
