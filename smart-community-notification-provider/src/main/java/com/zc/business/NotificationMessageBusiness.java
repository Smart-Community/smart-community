package com.zc.business;

import com.zc.pojo.NotificationMessage;

/**
 * @author 小帅气
 * @create 2020-02-07-20:43
 */
public interface NotificationMessageBusiness {

    NotificationMessage createNotification(long adminId, int typeId, String top, String desc);

    com.zc.vo.Page queryNotification(int pageSize, int pageNum, Integer type,Integer state, Long startTime, long endTime);

    NotificationMessage save(NotificationMessage notification);

    NotificationMessage findById(Long id);

}
