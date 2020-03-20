package com.zc.business;

import com.zc.pojo.House;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-02-06-23:13
 */
public interface HouseBusiness {
    House findByTungIdAndUnitIdAndNumber(int tungId,int unitId,int number);

    Page<House> queryByPage(Integer tungId, Integer unitId, Integer number, BigDecimal maxArea, BigDecimal minArea, Integer state, Integer pageIndex, Integer pageSize);

    int updateFee(BigDecimal unit);

    int autoUpdateState();

    int payFee(Long id, BigDecimal money, Integer type);

    int totalByUserId(Long userId);

    List<Map<String,Object>> queryByUserId(Long userId,Integer pageIndex,Integer pageSize);

    void payManager(Long houseId,Long adminId,BigDecimal money);
}
