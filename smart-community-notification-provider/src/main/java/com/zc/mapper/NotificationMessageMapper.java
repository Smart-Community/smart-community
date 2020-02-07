package com.zc.mapper;

import com.zc.pojo.NotificationMessage;
import com.zc.vo.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 小帅气
 * @create 2020-02-07-21:44
 */
public interface NotificationMessageMapper {
    List<NotificationMessage> queryNotificationMessage(@Param("page") Page page, @Param("startTime") long startTime,
                                                       @Param("endTime") long endTime);
}
