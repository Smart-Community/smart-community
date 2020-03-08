package com.zc.client.hystrix;

import com.zc.client.NotificationTypeClient;
import com.zc.util.CommonConstants;
import com.zc.vo.ResultWrap;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-03-08-22:33
 */
@Service
public class NotificationTypeHystrix implements NotificationTypeClient {
    @Override
    public Map<String, Object> getNotificationType() {
        return ResultWrap.init(CommonConstants.FALIED,"查询失败");
    }
}
