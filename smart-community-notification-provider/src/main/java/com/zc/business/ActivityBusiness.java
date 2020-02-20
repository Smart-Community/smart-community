package com.zc.business;

import com.zc.pojo.ActivityInformation;

import java.util.Date;

/**
 * @author 小帅气
 * @create 2020-02-19-20:13
 */
public interface ActivityBusiness {

    boolean createNewActivity(String name, String desc, long releaserId, Date startTime,Date joinTime,Integer sum,int limit);

    ActivityInformation update(ActivityInformation activityInformation);

    void join(Long userId,Long activityId);

    void cancelJoin(Long userId,Long activityId);

}
