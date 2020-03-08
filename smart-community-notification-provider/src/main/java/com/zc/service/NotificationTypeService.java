package com.zc.service;

import com.zc.business.NotificationTypeBusiness;
import com.zc.util.CommonConstants;
import com.zc.vo.ResultWrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.stream.events.Comment;
import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-03-08-22:35
 */
@RestController
public class NotificationTypeService {

    @Autowired
    private NotificationTypeBusiness notificationTypeBusiness;

    @GetMapping("/v1.0/notification/type/get")
    public Map<String, Object> getNotificationType() {
        return ResultWrap.init(CommonConstants.SUCCESS, "查询成功",
                notificationTypeBusiness.getNotificationTypeList());
    }

}
