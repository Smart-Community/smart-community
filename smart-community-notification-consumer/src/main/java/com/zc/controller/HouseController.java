package com.zc.controller;

import com.zc.client.HouseClient;
import com.zc.util.CommonConstants;
import com.zc.util.RedisUtil;
import com.zc.util.TokenUtil;
import com.zc.util.VerifyTokenUtil;
import com.zc.vo.LayuiVO;
import com.zc.vo.ResultWrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author 小帅气
 * @create 2020-03-12-21:45
 */
@RestController
public class HouseController {

    @Resource
    private HouseClient houseClient;
    @Autowired
    private VerifyTokenUtil verifyTokenUtil;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/public/houseinfo/query/page")
    public LayuiVO queryHouseInfoByPage(@RequestParam("token") String token,
                                        @RequestParam(value = "tungId", required = false) Integer tungId,
                                        @RequestParam(value = "unitId", required = false) Integer unitId,
                                        @RequestParam(value = "number", required = false) Integer number,
                                        @RequestParam(value = "maxArea", required = false) BigDecimal maxArea,
                                        @RequestParam(value = "minArea", required = false) BigDecimal minArea,
                                        @RequestParam(value = "state", required = false) Integer state,
                                        @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageIndex,
                                        @RequestParam(value = "limit", required = false, defaultValue = "20") Integer pageSize) {
        if (!verifyTokenUtil.verify(token, "admin")) {
            return null;
        }
        return houseClient.queryHouseInfo(tungId, unitId, number, maxArea, minArea, state, pageIndex, pageSize);
    }

    @GetMapping("/public/house/byuser/{userId}")
    public LayuiVO queryHouseByUserId(@PathVariable("userId") Long userId,
                                      @RequestParam("token") String token,
                                      @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageIndex,
                                      @RequestParam(value = "limit", required = false, defaultValue = "20") Integer pageSize) {
        if (!verifyTokenUtil.verify(token, "admin")) {
            return null;
        }
        return houseClient.queryHouseByUserId(userId, pageIndex, pageSize);
    }


    @PostMapping("/admin/house/pay/managent/{houseId}")
    public Object payManagent(@PathVariable("houseId") Long houseId,
                              @RequestHeader("token") String token,
                              @RequestParam("money") BigDecimal money) {
        Long adminId;
        final String key = "/admin/house/pay/managent/" + houseId;
        if (!redisUtil.lock(key, 60L)) {
            return ResultWrap.init(CommonConstants.FALIED, "该房子缴费中,请稍后再试");
        }
        try {
            adminId = TokenUtil.getUserId(token);
        } catch (Exception e) {
            return ResultWrap.init(CommonConstants.ERROR_TOKEN, "token无效");
        } finally {
            redisUtil.unLock(key);
        }
        return houseClient.payManagent(houseId, adminId, money);
    }

    @RequestMapping("/public/house/query/byuserid")
    public LayuiVO queryHouseByUserId(@RequestParam("token") String token,
                                      @RequestParam(value = "userId", required = false) Long userId,
                                      @RequestParam(value = "page", required = false, defaultValue = "0") Integer pageIndex,
                                      @RequestParam(value = "limit", required = false, defaultValue = "20") Integer pageSize) {
        Long toUserId;
        try {
            toUserId = TokenUtil.getUserId(token);
        } catch (Exception e) {
            return null;
        }
        if (!verifyTokenUtil.verify(token, "user")) {
            return null;
        }
        if (userId == null) {
            return houseClient.queryHouseByUserId(toUserId, pageIndex, pageSize);
        }
        return houseClient.queryHouseByUserId(userId, pageIndex, pageSize);
    }

    @GetMapping("/user/house/query")
    public Object queryHouse(@RequestHeader("token")String token){
        Long userId;
        try {
            userId = TokenUtil.getUserId(token);
        } catch (Exception e) {
            return ResultWrap.init(CommonConstants.FALIED,"token无效");
        }
        return houseClient.queryHouse(userId);
    }
}
