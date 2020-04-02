package com.zc.service;

import com.zc.business.ActivityBusiness;
import com.zc.pojo.ActivityInformation;
import com.zc.util.CommonConstants;
import com.zc.util.RedisUtil;
import com.zc.vo.LayuiVO;
import com.zc.vo.ResultWrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-02-17-15:29
 */
@RestController
public class AcitvityService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ActivityBusiness activityBusiness;


    @RequestMapping("/v1.0/activity/create")
    public Map<String, Object> createNewActivity(@RequestParam("releaseId") Long releaseId,
                                                 @RequestParam("name") String name,
                                                 @RequestParam("desc") String desc,
                                                 @RequestParam("number") Integer number,
                                                 @RequestParam("limit") short limit,
                                                 @RequestParam("startTime") Date startTime,
                                                 @RequestParam("joinTime") Date joinTime) {
        activityBusiness.createNewActivity(releaseId, name, desc, startTime, joinTime, number, limit);
        return ResultWrap.init(CommonConstants.SUCCESS, "新建活动成功");
    }

    @PostMapping("/v1.0/activity/update")
    public Map<String, Object> updateAcitvity(@RequestParam("id") Long id) {
        ActivityInformation byId = activityBusiness.findById(id);
        if (byId == null) {
            return ResultWrap.init(CommonConstants.FALIED, "该活动不存在");
        }
        if (byId.getStatus() == 2) {
            return ResultWrap.init(CommonConstants.FALIED,"活动已开始,不可取消");
        }
        byId.setStatus(4);
        activityBusiness.update(byId);
        return ResultWrap.init(CommonConstants.SUCCESS, "修改成功");
    }


    @PostMapping("/v1.0/activity/query/page")
    public LayuiVO queryActivy(@RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "status", required = false) Integer status,
                               @RequestParam("pageIndex") Integer pageIndex,
                               @RequestParam("pageSize") Integer pageSize) {
        return activityBusiness.queryActiveList(name, status, pageIndex, pageSize);
    }

}
