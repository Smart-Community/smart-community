package com.zc.business.impl;

import com.zc.business.FaultMaintenanceBusiness;
import com.zc.mapper.FaultMaintenanceMapper;
import com.zc.pojo.FaultMaintenance;
import com.zc.repository.FaultMaintenanceRepository;
import com.zc.vo.FaultVO;
import com.zc.vo.LayuiVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author 小帅气
 * @create 2020-02-07-22:15
 */
@Service
public class FaultMaintenanceBusinessImpl implements FaultMaintenanceBusiness {
    @Autowired
    private FaultMaintenanceRepository faultMaintenanceRepository;

    @Resource
    private FaultMaintenanceMapper faultMaintenanceMapper;


    @Override
    public FaultMaintenance save(FaultMaintenance faultMaintenance) {
        return faultMaintenanceRepository.save(faultMaintenance);
    }

    //事务回滚
//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    @Override
    @Transactional
    public FaultMaintenance createFaultMaintenance(long userId, int typeId, String desc, String address, String phone) {
        Date date = new Date();
        FaultMaintenance faultMaintenance = new FaultMaintenance().setFaultMaintenanceUserId(userId)
                .setFaultMaintenanceFaultTypeId(typeId).setFaultMaintenanceDescribe(desc)
                .setFaultMaintenanceAddrss(address).setFaultMaintenancePhone(phone)
                .setFaultMaintenanceReleaseTime(date)
                .setFaultMaintenanceReleaseTimeUnix(date.getTime());
        return faultMaintenanceRepository.save(faultMaintenance);
    }

    @Override
    public LayuiVO queryFaultHistory(Long userId, Integer type, Integer state, Integer pageIndex, Integer pageSize) {
        List<FaultVO> content = faultMaintenanceMapper.queryFaultHistory(userId, type, state,
                (pageIndex - 1) * pageSize,
                pageSize);
        int count = faultMaintenanceMapper.countQuery(userId, type, state);
        LayuiVO layuiVO = new LayuiVO();
        layuiVO.setCount(count);
        layuiVO.setData(content);
        return layuiVO;
    }

    @Override
    public FaultMaintenance updateState(Long id, Integer state) {
        Optional<FaultMaintenance> byId = faultMaintenanceRepository.findById(id);
        FaultMaintenance faultMaintenance = byId.get();
        faultMaintenance.setFaultMaintenanceState(state).setUpdateTime(new Date());
        return  faultMaintenanceRepository.saveAndFlush(faultMaintenance);
    }
}
