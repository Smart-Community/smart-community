package com.zc.business.impl;

import com.zc.business.FaultTypeBusiness;
import com.zc.pojo.FaultType;
import com.zc.repository.FaultTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 小帅气
 * @create 2020-03-29-9:54
 */
@Service
public class FaultTypeBusinessImpl implements FaultTypeBusiness {

    @Autowired
    private FaultTypeRepository faultTypeRepository;

    @Override
    public List<FaultType> faultTypeList() {
        return faultTypeRepository.findAll();
    }

    @Override
    public FaultType createFautlType(String name) {
        FaultType faultType = new FaultType();
        faultType.setFaultTypeName(name);
        return faultTypeRepository.saveAndFlush(faultType);
    }
}
