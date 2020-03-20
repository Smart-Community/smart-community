package com.zc.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-03-12-22:12
 */
public interface HouseMapper {
    List<Map<String, Object>> queryHouseInfoByUserId(@Param("userId") Long userId,
                                                     @Param("pageIndex") Integer pageIndex,
                                                     @Param("pageSize") Integer pageSize);
}
