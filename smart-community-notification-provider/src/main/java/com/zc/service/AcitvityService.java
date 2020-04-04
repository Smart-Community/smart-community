package com.zc.service;

import com.zc.business.ActivityBusiness;
import com.zc.pojo.ActivityInformation;
import com.zc.util.CommonConstants;
import com.zc.util.RedisUtil;
import com.zc.vo.LayuiVO;
import com.zc.vo.ResultWrap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
            return ResultWrap.init(CommonConstants.FALIED, "活动已开始,不可取消");
        }
        byId.setStatus(4);
        activityBusiness.update(byId);
        return ResultWrap.init(CommonConstants.SUCCESS, "修改成功");
    }

    @PostMapping("/v1.0/activity/end")
    public Map<String, Object> endAcitvity(@RequestParam("id") Long id) {
        ActivityInformation byId = activityBusiness.findById(id);
        if (byId == null) {
            return ResultWrap.init(CommonConstants.FALIED, "该活动不存在");
        }
        if (byId.getStatus() != 2) {
            return ResultWrap.init(CommonConstants.FALIED, "活动还未开始");
        }
        byId.setStatus(3);
        activityBusiness.update(byId);
        return ResultWrap.init(CommonConstants.SUCCESS, "结束成功");
    }


    @PostMapping("/v1.0/activity/query/page")
    public LayuiVO queryActivy(@RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "status", required = false) Integer status,
                               @RequestParam("pageIndex") Integer pageIndex,
                               @RequestParam("pageSize") Integer pageSize) {
        return activityBusiness.queryActiveList(name, status, pageIndex, pageSize);
    }

    @PostMapping("/v1.0/activity/query/page/userid")
    public LayuiVO queryByUserActivy(@RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "status", required = false) Integer status,
                                     @RequestParam(value = "userId", required = false) Long userId,
                                     @RequestParam("pageIndex") Integer pageIndex,
                                     @RequestParam("pageSize") Integer pageSize) {
        if (StringUtils.isBlank(name)) {
            name = null;
        }
        return activityBusiness.queryUserActiveList(name, status, userId, pageIndex, pageSize);
    }

    @PostMapping("/v1.0/activity/join")
    public Object joinBy(@RequestParam("userId") Long userId,
                         @RequestParam("id") Long id) {
        if (activityBusiness.queryByUserIdAndActivityId(userId, id) > 0) {
            return ResultWrap.init(CommonConstants.FALIED, "已参加");
        }
        activityBusiness.join(userId, id);
        return ResultWrap.init(CommonConstants.SUCCESS, "报名成功");
    }

    @PostMapping("/v1.0/activity/person/query")
    public LayuiVO queryActivityPerson(@RequestParam("id") Long id,
                                       @RequestParam("limit")Integer pageSize,
                                       @RequestParam("page")Integer pageIndex) {
        return activityBusiness.queryActivityPerson(id,pageIndex,pageSize);
    }
}
