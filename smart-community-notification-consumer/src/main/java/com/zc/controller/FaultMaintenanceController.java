package com.zc.controller;

import com.zc.client.FaultClient;
import com.zc.util.CommonConstants;
import com.zc.util.TokenUtil;
import com.zc.util.VerifyTokenUtil;
import com.zc.vo.LayuiVO;
import com.zc.vo.ResultWrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 小帅气
 * @create 2020-03-19-22:30
 */
@RestController
public class FaultMaintenanceController {

    @Autowired
    private VerifyTokenUtil verifyTokenUtil;
    @Resource
    private FaultClient faultClient;

    @PostMapping("/public/user/fault/query")
    public LayuiVO queryFault(@RequestParam("token") String token,
                              @RequestParam(value = "userId", required = false) Long userId,
                              @RequestParam(value = "type", required = false) Integer type,
                              @RequestParam(value = "state", required = false) Integer state,
                              @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageIndex,
                              @RequestParam(value = "limit", required = false, defaultValue = "20") Integer pageSize) {

        Long toUserId;
        try {
            toUserId = TokenUtil.getUserId(token);
        } catch (Exception e) {
            return null;
        }
        if (!verifyTokenUtil.verify(token, "admin") && verifyTokenUtil.verify(token, "user")) {
            return faultClient.queryFaultHistory(toUserId, type, state, pageIndex, pageSize);
        }
        if (verifyTokenUtil.verify(token, "admin")) {
            return faultClient.queryFaultHistory(null, type, state, pageIndex, pageSize);
        }

        return null;

    }

    @PostMapping("/user/fault/add")
    public Object addFault(@RequestHeader("token") String token,
                           @RequestParam("type") Integer type,
                           @RequestParam("desc") String desc,
                           @RequestParam("addr") String addr,
                           @RequestParam("phone") String phone) {
        Long userId;
        try {
            userId = TokenUtil.getUserId(token);
        } catch (Exception e) {
            return ResultWrap.init(CommonConstants.ERROR_TOKEN, "token无效");
        }
        return faultClient.addFault(userId, type, desc, addr, phone);
    }

    @PostMapping("/user/fault/cancel")
    public Object updateState(@RequestParam("id") Long id,
                              @RequestParam("state") Integer state) {
        return ResultWrap.init(CommonConstants.SUCCESS, "", faultClient.updateState(id, state));
    }
}
