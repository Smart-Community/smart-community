package com.zc.client.hystrix;

import com.zc.client.FaultClient;
import com.zc.vo.LayuiVO;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-03-20-22:58
 */
@Service
public class FaultHystrix implements FaultClient {
    @Override
    public Map<String, Object> addFault(Long userId, Integer type, String desc, String addr, String phone) {
        return null;
    }

    @Override
    public LayuiVO queryFaultHistory(Long userId, Integer type, Integer state, Integer pageIndex, Integer pageSize) {
        return null;
    }
}
