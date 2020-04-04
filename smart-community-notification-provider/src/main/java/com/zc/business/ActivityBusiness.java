package com.zc.business;

import com.zc.pojo.ActivityInformation;
import com.zc.vo.ActiveVO;
import com.zc.vo.LayuiVO;

import java.util.Date;
import java.util.List;

/**
 * @author 小帅气
 * @create 2020-02-19-20:13
 */
public interface ActivityBusiness {

    void createNewActivity(Long userId, String name, String desc, Date startTime, Date joinTime, Integer sum,
                           short limit);

    ActivityInformation update(ActivityInformation activityInformation);

    void join(Long userId, Long activityId);

    void cancelJoin(Long userId, Long activityId);

    LayuiVO queryActiveList(String name, Integer status, Integer pageIndex, Integer pageSize);

    ActivityInformation findById(Long id);

    List<ActivityInformation> findListByState(Integer state);

    LayuiVO queryUserActiveList(String name, Integer status, Long userId, Integer pageIndex, Integer pageSize);

    int queryByUserIdAndActivityId(Long userId,Long id);

    LayuiVO queryActivityPerson(Long id,Integer pageIndex,Integer pageSize);
}
