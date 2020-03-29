package com.zc.client.hystrix;

import com.zc.client.FaultTypeClient;
import com.zc.util.CommonConstants;
import com.zc.vo.ResultWrap;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-03-29-10:03
 */
@Service
public class FaultTypeHystrix implements FaultTypeClient {
    @Override
    public Map<String, Object> queryFaultType() {
        return ResultWrap.init(CommonConstants.FALIED,"系统小憩了,请稍后再试");
    }

    @Override
    public Map<String, Object> addFaultType(String name) {
        return ResultWrap.init(CommonConstants.FALIED,"系统小憩了,请稍后再试");
    }
}
