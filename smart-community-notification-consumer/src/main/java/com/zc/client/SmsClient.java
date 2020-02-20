package com.zc.client;

import com.zc.client.hystrix.SmsClientHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 小帅气
 * @create 2020-02-07-16:00
 */
@FeignClient(name = "provider", fallback = SmsClientHystrix.class)
public interface SmsClient {
    @RequestMapping("/v1.0/sms/send")
    public Object sendSms(@RequestParam("phone") String phone);
}
