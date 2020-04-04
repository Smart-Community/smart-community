package com.zc.mapper;

import com.zc.vo.ActiveVO;
import com.zc.vo.PersonVo;
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

    List<ActiveVO> queryUserActiveList(@Param("name") String name,
                                       @Param("status") Integer status,
                                       @Param("userId") Long userId,
                                       @Param("pageIndex") Integer pageIndex,
                                       @Param("pageSize") Integer pageSize);

    int queryCountUser(@Param("name") String name, @Param("status") Integer status, @Param("userId") Long userId);

    List<PersonVo> queryActivityPerson(@Param("id") Long id,
                                       @Param("pageIndex") Integer pageIndex,
                                       @Param("pageSize") Integer pageSize);

    int countActivityPerson(@Param("id") Long id);

    ;

}
