package com.zc.mapper;

import com.zc.vo.NotificationVO;
import com.zc.util.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 小帅气
 * @create 2020-02-07-21:44
 */
public interface NotificationMessageMapper {
    List<NotificationVO> queryNotificationMessage(@Param("page") Page page, @Param("type") Integer type,
                                                  @Param("state") Integer state, @Param("startTime") long startTime,
                                                  @Param("endTime") long endTime);
}
