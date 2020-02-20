package com.zc.business;

import com.zc.pojo.NotificationMessage;
import com.zc.vo.Page;

/**
 * @author 小帅气
 * @create 2020-02-07-20:43
 */
public interface NotificationMessageBusiness {

    NotificationMessage createNotification(long adminId, int typeId, String top, String desc);

    Page queryNotification(int pageSize, int pageNum, Integer type, Long startTime, long endTime);

    NotificationMessage save(NotificationMessage notification);

}
