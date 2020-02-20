package com.zc.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author 小帅气
 * @create 2020-02-19-20:32
 */
public interface ActivityMapper {

    int updateJoinNum(@Param("activityId")Long activityId,@Param("num")int num);

}
