package com.zc.controller;

import com.zc.client.ActivityClient;
import com.zc.enums.AcitveConstants;
import com.zc.util.CommonConstants;
import com.zc.util.RedisUtil;
import com.zc.util.TokenUtil;
import com.zc.util.VerifyTokenUtil;
import com.zc.vo.LayuiVO;
import com.zc.vo.ResultWrap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.xml.transform.Result;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author 小帅气
 * @create 2020-02-17-15:24
 */
@RestController
public class AcitvityController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private VerifyTokenUtil verifyTokenUtil;

    @Resource
    private ActivityClient activityClient;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @PostMapping("/public/activity/page")
    public LayuiVO pageActivity(@RequestParam("token") String token,
                                @RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "status", required = false) Integer status,
                                @RequestParam(value = "limit", required = false, defaultValue = "20") Integer pageSize,
                                @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageIndex) {

        if (!verifyTokenUtil.verify(token, "user")) {
            return null;
        }
        if (StringUtils.isBlank(name)) {
            name = null;
        }
        return activityClient.queryActivy(name, status, pageIndex, pageSize);
    }

    @PostMapping("/public/activity/page/user")
    public LayuiVO pageByUserActivity(@RequestParam("token") String token,
                                      @RequestParam(value = "name", required = false) String name,
                                      @RequestParam(value = "status", required = false) Integer status,
                                      @RequestParam(value = "limit", required = false, defaultValue = "20") Integer pageSize,
                                      @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageIndex) {

        if (!verifyTokenUtil.verify(token, "user")) {
            return null;
        }
        Long userId;
        try {
            userId = TokenUtil.getUserId(token);
        } catch (Exception e) {
            return null;
        }
        if (StringUtils.isBlank(name)) {
            name = null;
        }
        return activityClient.queryByUserActivy(name, status, userId, pageIndex, pageSize);
    }

    @PostMapping("/admin/activity/add")
    public Object createActivity(@RequestHeader("token") String token,
                                 @RequestParam("name") String name,
                                 @RequestParam("desc") String desc,
                                 @RequestParam("number") Integer number,
                                 @RequestParam("limit") short limit,
                                 @RequestParam("startTime") String startTime,
                                 @RequestParam("joinTime") String joinTime) {
        Long userId;
        try {
            userId = TokenUtil.getUserId(token);
        } catch (Exception e) {
            return ResultWrap.init(CommonConstants.ERROR_TOKEN, "token无效");
        }
        Date start = null;
        Date join = null;
        try {
            start = simpleDateFormat.parse(startTime);
            join = simpleDateFormat.parse(joinTime);
        } catch (ParseException e) {
            return ResultWrap.init(CommonConstants.FALIED, "时间格式错误");
        }
        return activityClient.createNewActivity(userId, name, desc, number, limit, start, join);
    }

    @PostMapping("/admin/activity/cancel")
    public Object cancelActivity(@RequestParam("id") Long id) {
        return activityClient.updateAcitvity(id);
    }

    @PostMapping("/admin/activity/end")
    public Object endActivity(@RequestParam("id") Long id) {
        return activityClient.endAcitvity(id);
    }

    @PostMapping("/user/adctivity/join")
    public Object joinActivity(@RequestHeader("token") String token,
                               @RequestParam("id") Long id) {
        Long userId;
        try {
            userId = TokenUtil.getUserId(token);
        } catch (Exception e) {
            return ResultWrap.init(CommonConstants.FALIED, "token无效");
        }
        final String key = id + "-" + userId;
        if (stringRedisTemplate.opsForValue().increment(key) != 1) {
            return ResultWrap.init(CommonConstants.FALIED, "已参加");
        }
        stringRedisTemplate.opsForValue().set(key, "0", 5, TimeUnit.HOURS);
        final String activKey = AcitveConstants.ACTIVE_KEY.getKey() + id + AcitveConstants.NUM_KEY.getKey();
        if (stringRedisTemplate.opsForValue().increment(activKey, -1L) < 0) {
            stringRedisTemplate.opsForValue().increment(activKey, 1L);
            return ResultWrap.init(CommonConstants.FALIED, "报名人数已满");
        }
        return activityClient.joinBy(userId, id);
    }

    @PostMapping("/public/activity/person/query")
    public LayuiVO queryActivityPerson(@RequestParam("id") Long id,
                                       @RequestParam("token") String token,
                                       @RequestParam("limit") Integer pageSize,
                                       @RequestParam("page") Integer pageIndex) {
        if (!verifyTokenUtil.verify(token, "admin")) {
            return null;
        }
        return activityClient.queryActivityPerson(id, pageSize, pageIndex);
    }

}
