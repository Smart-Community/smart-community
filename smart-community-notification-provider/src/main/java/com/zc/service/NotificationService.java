package com.zc.service;

import com.zc.business.NotificationMessageBusiness;
import com.zc.pojo.NotificationMessage;
import com.zc.util.CommonConstants;
import com.zc.vo.ResultWrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-02-12-21:54
 */
@RestController
public class NotificationService {
    @Autowired
    private NotificationMessageBusiness notificationMessageBusiness;

    @PostMapping("/v1.0/notification/add")
    public Map<String, Object> addNotification(@RequestParam("adminId") long adminId,
                                               @RequestParam("typeId") int typeId,
                                               @RequestParam("top") String top,
                                               @RequestParam("desc") String desc) {
        NotificationMessage notificationMessage = notificationMessageBusiness.createNotification(adminId, typeId, top
                , desc);
        return ResultWrap.init(CommonConstants.SUCCESS, "发布通知成功", notificationMessage);
    }
}
