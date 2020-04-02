package com.zc.scanner;

import com.zc.business.ActivityBusiness;
import com.zc.enums.AcitveConstants;
import com.zc.pojo.ActivityInformation;
import com.zc.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author 小帅气
 * @create 2020-04-02-21:25
 */
@Component
public class ActivityScanner {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ActivityBusiness activityBusiness;

    @Scheduled(cron = "0 0/1 * * * ? ")
    public void scannerActivity() {
        LOG.info("*****扫描所有已发布活动*****");
        handle(0);
        LOG.info("*****处理所有已发布活动*****");
        LOG.info("*****扫描所有报名中活动*****");
        handle(1);
        LOG.info("*****处理所有报名中活动*****");
    }

    private void handle(int state) {
        List<ActivityInformation> listByState = activityBusiness.findListByState(state);
        Date now = new Date();
        if (state == 0) {
            for (ActivityInformation activityInformation : listByState) {
                if (now.compareTo(activityInformation.getJoinTime()) > 0) {
                    activityInformation.setStatus(1);
                    activityBusiness.update(activityInformation);
                    // 操作redis
                    redisUtil.setStr(AcitveConstants.ACTIVE_KEY.getKey() + activityInformation.getActivityInformationId() + AcitveConstants.NUM_KEY.getKey(),
                            activityInformation.getActivityInformationNumber() + "");
                }
            }
        }

        if (state == 1) {
            for (ActivityInformation activityInformation : listByState) {
                if (now.compareTo(activityInformation.getJoinTime()) > 0) {
                    activityInformation.setStatus(2);
                    activityBusiness.update(activityInformation);
                    // 操作redis
                    redisUtil.del(AcitveConstants.ACTIVE_KEY.getKey() + activityInformation.getActivityInformationId() + AcitveConstants.NUM_KEY.getKey());
                }
            }
        }
    }


}
