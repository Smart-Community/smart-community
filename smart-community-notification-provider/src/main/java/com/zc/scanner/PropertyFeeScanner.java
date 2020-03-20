package com.zc.scanner;

import com.zc.business.HouseBusiness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author 小帅气
 * @create 2020-02-23-22:17
 */
@Component
public class PropertyFeeScanner {

    @Value("${house.unit}")
    private BigDecimal unit;

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private HouseBusiness houseBusiness;


    @Scheduled(cron = "0 0 0 1 * ?")
    private void deduct() {
        LOG.info("*****自动扣除物业费开始了*****");
        houseBusiness.updateFee(unit);
        LOG.info("*****自动扣除物业费结束了*****");
        LOG.info("*****自动更改房屋状态开始了*****");
        houseBusiness.autoUpdateState();
        LOG.info("*****自动更改房屋状态结束了*****");
    }

}
