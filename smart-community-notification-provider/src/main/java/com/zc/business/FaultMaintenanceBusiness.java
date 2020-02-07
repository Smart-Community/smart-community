package com.zc.business;

import com.zc.pojo.FaultMaintenance;

/**
 * @author 小帅气
 * @create 2020-02-07-22:12
 */
public interface FaultMaintenanceBusiness {

    FaultMaintenance save(FaultMaintenance faultMaintenance);

    FaultMaintenance createFaultMaintenance( long userId,int typeId,String desc,String address,String phone);
}
