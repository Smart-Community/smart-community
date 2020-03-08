package com.zc.controller;

import com.zc.client.NotificationTypeClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小帅气
 * @create 2020-03-08-22:31
 */
@RestController
public class NotificationTypeController {

    @Resource
    private NotificationTypeClient notificationTypeClient;


    @GetMapping("/admin/notification/type/get")
    public Object getNotificationType(){
        return notificationTypeClient.getNotificationType();
    }
}
