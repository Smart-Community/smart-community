package com.zc.business.impl;

import com.zc.business.NotificationMessageBusiness;
import com.zc.mapper.NotificationMessageMapper;
import com.zc.pojo.NotificationMessage;
import com.zc.repository.NotificationMessageRepository;
import com.zc.vo.NotificationVO;
import com.zc.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

    @Resource
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
    public com.zc.vo.Page queryNotification(int pageSize, int pageNum, Integer type,Integer state, Long startTime, long endTime) {
        Page page = new Page();
        page.setCurrPageNo(pageNum);
        page.setPageSize(pageSize);
        List<NotificationVO> list = notificationMessageMapper.queryNotificationMessage(page, type, state,startTime,
                endTime);
        int total = (int) notificationMessageRepository.count();
        com.zc.vo.Page<NotificationVO> pages = new com.zc.vo.Page<>();
        pages.setTotal(total);
        pages.setContent(list);
        return pages;
    }

    @Override
    public NotificationMessage save(NotificationMessage notification) {
        return notificationMessageRepository.save(notification);
    }

    @Override
    public NotificationMessage findById(Long id) {
        return notificationMessageRepository.findById(id).get();
    }

}
