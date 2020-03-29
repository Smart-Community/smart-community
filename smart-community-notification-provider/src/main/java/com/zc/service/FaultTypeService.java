package com.zc.service;

import com.zc.business.FaultTypeBusiness;
import com.zc.business.impl.FaultTypeBusinessImpl;
import com.zc.pojo.FaultType;
import com.zc.util.CommonConstants;
import com.zc.vo.ResultWrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-02-12-21:53
 */
@RestController
public class FaultTypeService {

    @Autowired
    private FaultTypeBusiness faultTypeBusiness;

    @RequestMapping("/v1.0/fault/type/query")
    public Map<String, Object> queryFaultType() {
        return ResultWrap.init(CommonConstants.SUCCESS, "", faultTypeBusiness.faultTypeList());
    }

    @RequestMapping("/v1.0/fault/type/add")
    public Map<String, Object> addFaultType(@RequestParam("name") String name) {
        List<FaultType> faultTypeList = faultTypeBusiness.faultTypeList();
        for (FaultType faultType : faultTypeList) {
            if (faultType.getFaultTypeName().equals(name)) {
                return ResultWrap.init(CommonConstants.FALIED, "该类型已存在");
            }
        }
        return ResultWrap.init(CommonConstants.SUCCESS, "添加成功", faultTypeBusiness.createFautlType(name));
    }

}
