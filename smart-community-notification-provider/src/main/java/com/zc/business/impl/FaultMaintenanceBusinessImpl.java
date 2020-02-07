package com.zc.business.impl;

import com.zc.business.FaultMaintenanceBusiness;
import com.zc.pojo.FaultMaintenance;
import com.zc.repository.FaultMaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author 小帅气
 * @create 2020-02-07-22:15
 */
@Service
public class FaultMaintenanceBusinessImpl implements FaultMaintenanceBusiness {
    @Autowired
    private FaultMaintenanceRepository faultMaintenanceRepository;

    @Override
    public FaultMaintenance save(FaultMaintenance faultMaintenance) {
        return faultMaintenanceRepository.save(faultMaintenance);
    }

    @Override
    @Transactional
    public FaultMaintenance createFaultMaintenance(long userId, int typeId, String desc, String address, String phone) {
        Date date = new Date();
        FaultMaintenance faultMaintenance = new FaultMaintenance().setFaultMaintenanceUserId(userId)
                .setFaultMaintenanceFaultTypeId(typeId).setFaultMaintenanceDescribe(desc)
                .setFaultMaintenanceAddrss(address).setFaultMaintenancePhone(phone).setFaultMaintenanceReleaseTime(date)
                .setFaultMaintenanceReleaseTimeUnix(date.getTime());
        return faultMaintenanceRepository.save(faultMaintenance);
    }


}
