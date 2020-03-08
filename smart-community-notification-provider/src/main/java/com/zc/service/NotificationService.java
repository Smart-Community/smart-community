package com.zc.service;

import com.zc.business.NotificationMessageBusiness;
import com.zc.pojo.NotificationMessage;
import com.zc.util.CommonConstants;
import com.zc.vo.ResultWrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-02-12-21:54
 */
@RestController
public class NotificationService {
    @Autowired
    private NotificationMessageBusiness notificationMessageBusiness;

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @PostMapping("/v1.0/notification/add")
    public Map<String, Object> addNotification(@RequestParam("adminId") long adminId,
                                               @RequestParam("typeId") int typeId,
                                               @RequestParam("top") String top,
                                               @RequestParam("desc") String desc) {
        NotificationMessage notificationMessage = notificationMessageBusiness.createNotification(adminId, typeId, top
                , desc);
        return ResultWrap.init(CommonConstants.SUCCESS, "发布通知成功", notificationMessage);
    }

    @PostMapping("/v1.0/notification/query")
    public Map<String, Object> queryNotification(@RequestParam(value = "pageSize", required = false, defaultValue =
            "20") Integer pageSize,
                                                 @RequestParam(value = "pageIndex", required = false, defaultValue =
                                                         "1") Integer pageIndex,
                                                 @RequestParam(value = "type", required = false) Integer type,
                                                 @RequestParam(value = "state", required = false) Integer state,
                                                 @RequestParam(value = "startTime") Long startTime,
                                                 @RequestParam(value = "endTime") Long endTime) {

        return ResultWrap.init(CommonConstants.SUCCESS, "查询成功",
                notificationMessageBusiness.queryNotification(pageSize, pageIndex, type, state, startTime, endTime));
    }

    @PostMapping("/v1.0/notification/state/update")
    public Map<String, Object> updateState(@RequestParam("id") Long id,
                                           @RequestParam("state") Integer state) {
        notificationMessageBusiness.save(notificationMessageBusiness.findById(id).setNotificationMessageState(state));
        return ResultWrap.init(CommonConstants.SUCCESS, "成功");
    }
}
