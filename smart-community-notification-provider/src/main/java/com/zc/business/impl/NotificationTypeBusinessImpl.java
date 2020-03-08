package com.zc.business.impl;

import com.zc.business.NotificationTypeBusiness;
import com.zc.pojo.NotificationMessageType;
import com.zc.repository.NotificationMessageTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 小帅气
 * @create 2020-03-08-22:36
 */
@Service
public class NotificationTypeBusinessImpl implements NotificationTypeBusiness {

    @Autowired
    private NotificationMessageTypeRepository notificationMessageTypeRepository;

    @Override
    public List<NotificationMessageType> getNotificationTypeList() {
        return notificationMessageTypeRepository.findAll();
    }
}
