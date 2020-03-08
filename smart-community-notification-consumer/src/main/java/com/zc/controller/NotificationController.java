package com.zc.controller;

import com.zc.client.NotificationClient;
import com.zc.util.CommonConstants;
import com.zc.vo.LayuiVO;
import com.zc.vo.ResultWrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-03-06-9:26
 */
@RestController
public class NotificationController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Resource
    private NotificationClient notificationClient;

    @RequestMapping("/public/notification/query")
    public Object queryNotification(@RequestParam(value = "startTime", required = false) String startTimeString,
                                    @RequestParam(value = "endTime", required = false) String endTimeString,
                                    @RequestParam(value = "type", required = false) Integer type,
                                    @RequestParam(value = "state", required = false, defaultValue = "2") Integer state,
                                    @RequestParam(value = "limit", required = false, defaultValue = "20") Integer pageSize,
                                    @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageIndex) {
        Long startTime, endTime;
        if (startTimeString == null) {
            Calendar c = Calendar.getInstance();
            //设置为1号,当前日期既为本月第一天
            c.set(Calendar.DAY_OF_MONTH, 1);
            //将小时至0
            c.set(Calendar.HOUR_OF_DAY, 0);
            //将分钟至0
            c.set(Calendar.MINUTE, 0);
            //将秒至0
            c.set(Calendar.SECOND, 0);
            //将毫秒至0
            c.set(Calendar.MILLISECOND, 0);
            //将年份设为2020
            c.set(Calendar.YEAR, 2020);
            //将月份设为1月
            c.set(Calendar.MONTH, 0);
            // 获取本月第一天的时间戳
            startTime = c.getTimeInMillis();
        } else {
            try {
                startTime = SimpleDateFormat.getDateTimeInstance().parse(startTimeString).getTime();
            } catch (ParseException e) {
                LOG.error("时间转化错误");
                return ResultWrap.init(CommonConstants.FALIED, "时间格式错误");
            }
        }
        if (endTimeString == null) {
            endTime = System.currentTimeMillis();
        } else {
            try {
                endTime = SimpleDateFormat.getDateTimeInstance().parse(startTimeString).getTime();
            } catch (ParseException e) {
                LOG.error("时间转化错误");
                return ResultWrap.init(CommonConstants.FALIED, "时间格式错误");
            }
        }
        if (state==2){
            state=null;
        }
        //改为layui使用vo
        Map<String, Object> map = notificationClient.queryNotification(pageSize, pageIndex, type, state, startTime,
                endTime);
        if (map.get(CommonConstants.RESP_CODE).equals(CommonConstants.SUCCESS)) {
            map = (Map<String, Object>) map.get(CommonConstants.RESULT);
            LayuiVO layuiVO = new LayuiVO();
            layuiVO.setCount(Integer.parseInt(map.get("total").toString()));
            layuiVO.setData((List<?>) map.get("content"));
            return layuiVO;
        }
        return ResultWrap.init(CommonConstants.FALIED, "查询失败");
    }

    @PostMapping("/admin/notification/state/update")
    public Object updateState(@RequestParam("id") Long id,
                              @RequestParam("state") Integer state) {
        return notificationClient.updateState(id, state);
    }
}
