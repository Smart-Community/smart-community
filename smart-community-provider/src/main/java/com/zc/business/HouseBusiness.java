package com.zc.business;

import com.zc.pojo.House;

/**
 * @author 小帅气
 * @create 2020-02-06-23:13
 */
public interface HouseBusiness {
    House findByTungIdAndUnitIdAndNumber(int tungId,int unitId,int number);
}
