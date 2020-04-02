package com.zc.mapper;

import com.zc.vo.ActiveVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 小帅气
 * @create 2020-02-19-20:32
 */
public interface ActivityMapper {

    int updateJoinNum(@Param("activityId") Long activityId, @Param("num") int num);

    List<ActiveVO> queryActiveList(@Param("name") String name, @Param("status") Integer status,
                                   @Param("pageIndex") Integer pageIndex,
                                   @Param("pageSize") Integer pageSize);

    int queryActiveCount(@Param("name") String name, @Param("status") Integer status);

}
