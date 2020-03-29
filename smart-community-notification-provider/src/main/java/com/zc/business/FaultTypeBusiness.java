package com.zc.business;

import com.zc.pojo.FaultType;

import java.util.List;

/**
 * @author 小帅气
 * @create 2020-03-29-9:54
 */
public interface FaultTypeBusiness {

    List<FaultType> faultTypeList();

    FaultType createFautlType(String name);
}
