package com.zc.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-03-19-22:42
 */
public interface FaultMaintenanceMapper {

    List<Map<String,Object>> queryFaultHistory(@Param("userId")Long userId,
                                               @Param("type")Integer type,
                                               @Param("state")Integer state,
                                               @Param("pageIndex")Integer pageIndex,
                                               @Param("pageSize")Integer pageSize);

    int countQuery(@Param("userId")Long userId,
                   @Param("type")Integer type,
                   @Param("state")Integer state,
                   @Param("pageIndex")Integer pageIndex,
                   @Param("pageSize")Integer pageSize);

}
