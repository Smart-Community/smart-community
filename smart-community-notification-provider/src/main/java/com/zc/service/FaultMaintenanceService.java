package com.zc.service;

import com.zc.business.FaultMaintenanceBusiness;
import com.zc.util.CommonConstants;
import com.zc.vo.LayuiVO;
import com.zc.vo.ResultWrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-03-19-22:37
 */
@RestController
public class FaultMaintenanceService {

    @Autowired
    private FaultMaintenanceBusiness faultMaintenanceBusiness;

    @RequestMapping("/v1.0/fault/history/query/{userId}")
    public LayuiVO queryFaultHistory(@PathVariable("userId") Long userId,
                                     @RequestParam(value = "type", required = false) Integer type,
                                     @RequestParam(value = "state", required = false) Integer state,
                                     @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageIndex,
                                     @RequestParam(value = "limit", required = false, defaultValue = "20") Integer pageSize) {


        return null;
    }

    @PostMapping("/v1.0/fault/add")
    public Map<String ,Object> addFault(@RequestParam("userId")Long userId,
                                        @RequestParam("type")Integer type,
                                        @RequestParam("desc")String desc,
                                        @RequestParam("addr")String addr,
                                        @RequestParam("phone")String phone){
        faultMaintenanceBusiness.createFaultMaintenance(userId,type,desc,addr,phone);
        return ResultWrap.init(CommonConstants.SUCCESS,"报修成功");
    }
}
