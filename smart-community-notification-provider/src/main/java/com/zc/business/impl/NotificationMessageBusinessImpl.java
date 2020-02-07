package com.zc.business.impl;

import com.zc.business.NotificationMessageBusiness;
import com.zc.mapper.NotificationMessageMapper;
import com.zc.pojo.NotificationMessage;
import com.zc.repository.NotificationMessageRepository;
import com.zc.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Notification;
import java.util.Date;
import java.util.List;

/**
 * @author 小帅气
 * @create 2020-02-07-20:43
 */
@Service
public class NotificationMessageBusinessImpl implements NotificationMessageBusiness {

    @Autowired
    private NotificationMessageRepository notificationMessageRepository;

    @Autowired
    private NotificationMessageMapper notificationMessageMapper;

    @Override
    @Transactional
    public NotificationMessage createNotification(long adminId, int typeId, String top, String desc) {
        NotificationMessage notificationMessage = new NotificationMessage();
        Date date = new Date();
        notificationMessage.setNotificationMessageAdminId(adminId)
                .setNotificationMessageTypeId(typeId)
                .setNotificationMessageTop(top)
                .setNotificationMessageDescribe(desc)
                .setNotificationMessageState(1)
                .setNotificationMessageReleaseTime(date)
                .setNotificationMessageReleaseTimeUnix(date.getTime());
        return notificationMessageRepository.save(notificationMessage);
    }

    @Override
    public Page queryNotification(int pageSize, int pageNum, Long startTime, long endTime) {
        Page page = new Page();
        page.setCurrPageNo(pageNum);
        page.setPageSize(pageSize);
        List<NotificationMessage> list = notificationMessageMapper.queryNotificationMessage(page, startTime, endTime);
        int total = (int) notificationMessageRepository.count();
        page.setTotalCount(total);
        page.setObjectList(list);
        return page;
    }

    @Override
    public NotificationMessage save(NotificationMessage notification) {
        return notificationMessageRepository.save(notification);
    }

}
