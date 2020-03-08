package com.zc.client.hystrix;

import com.zc.client.NotificationClient;
import com.zc.util.CommonConstants;
import com.zc.vo.ResultWrap;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-03-06-9:31
 */
@Service
public class NotificationHystrix implements NotificationClient {
    @Override
    public Map<String, Object> addNotification(long adminId, int typeId, String top, String desc) {
        return ResultWrap.init(CommonConstants.FALIED,"失败");
    }

    @Override
    public Map<String, Object> queryNotification(Integer pageSize, Integer pageIndex, Integer type, Integer state,
                                                 Long startTime, Long endTime) {
        return ResultWrap.init(CommonConstants.FALIED,"失败");
    }

    @Override
    public Map<String, Object> updateState(Long id, Integer state) {
        return ResultWrap.init(CommonConstants.FALIED,"失败");
    }


}
