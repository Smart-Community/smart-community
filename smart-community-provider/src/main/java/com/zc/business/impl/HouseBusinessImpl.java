package com.zc.business.impl;

import com.zc.business.HouseBusiness;
import com.zc.pojo.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 小帅气
 * @create 2020-02-06-23:14
 */
@Service
public class HouseBusinessImpl implements HouseBusiness {
    @Autowired

    @Override
    public House findByTungIdAndUnitIdAndNumber(int tungId, int unitId, int number) {
        return null;
    }
}
