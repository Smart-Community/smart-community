package com.zc.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-03-15-18:16
 */
public interface UserMapper {
    List<Map<String,Object>> findByHouseId(@Param("houseId") Long houseId);

    Integer countByHouseId(@Param("houseId")Long houseId);

}
