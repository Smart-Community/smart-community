package com.zc.client.hystrix;

import com.zc.client.SmsClient;
import org.springframework.stereotype.Component;

/**
 * @author 小帅气
 * @create 2020-02-07-16:00
 */
@Component
public class SmsClientHystrix implements SmsClient {
    @Override
    public Object sendSms(String phone) {
        return null;
    }
}
