package com.zc.client.hystrix;

import com.zc.client.HouseClient;
import com.zc.util.CommonConstants;
import com.zc.vo.LayuiVO;
import com.zc.vo.ResultWrap;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-03-12-21:44
 */
@Service
public class HouseHystrix implements HouseClient {
    @Override
    public LayuiVO queryHouseInfo(Integer tungId, Integer unitId, Integer number, BigDecimal maxArea,
                                  BigDecimal minArea, Integer state, Integer pageIndex, Integer pageSize) {
        return null;
    }

    @Override
    public LayuiVO queryHouseByUserId(Long userId, Integer pageIndex, Integer pageSize) {
        return null;
    }

    @Override
    public Map<String, Object> payManagent(Long houseId, Long adminId, BigDecimal money) {
        return ResultWrap.init(CommonConstants.FALIED,"系统异常");
    }
}
