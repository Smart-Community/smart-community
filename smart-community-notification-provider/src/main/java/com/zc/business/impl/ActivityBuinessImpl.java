package com.zc.business.impl;

import com.zc.business.ActivityBusiness;
import com.zc.mapper.ActivityMapper;
import com.zc.pojo.ActivityInformation;
import com.zc.pojo.ActivityRegistration;
import com.zc.repository.ActivityRegistrationRepository;
import com.zc.repository.ActivityRepository;
import com.zc.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 小帅气
 * @create 2020-02-19-20:14
 */
@Service
public class ActivityBuinessImpl implements ActivityBusiness {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityRegistrationRepository activityRegistrationRepository;

    @Resource
    private ActivityMapper activityMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createNewActivity(String name, String desc, long releaserId, Date startTime, Date joinTime,
                                     Integer sum, int limit) {

        //todo 操作redis
        return false;
    }

    @Override
    public ActivityInformation update(ActivityInformation activityInformation) {
        return activityRepository.save(activityInformation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void join(Long userId, Long activityId) {
        ActivityRegistration activityRegistration = new ActivityRegistration();
        activityRegistration.setActivityInformationId(activityId)
                .setActivityRegistrationUserId(userId)
                .setActivityRegistrationTime(new Date())
                .setStatus(0);
        activityRegistrationRepository.save(activityRegistration);
        activityMapper.updateJoinNum(activityId, 1);
        //todo 更改redis
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelJoin(Long userId, Long activityId) {
        ActivityRegistration activityRegistration = activityRegistrationRepository.findOne((root, criteriaQuery,
                                                                                            criteriaBuilder) -> {
            List<Predicate> predicatesList = new ArrayList<>();
            predicatesList.add(criteriaBuilder.equal(root.get("activityRegistrationUserId").as(Long.class), userId));
            predicatesList.add(criteriaBuilder.equal(root.get("activity_information_id").as(Long.class), activityId));
            return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
        }).get();
        activityRegistration.setStatus(0);
        activityMapper.updateJoinNum(activityId, -1);
        //todo 更改redis
    }

}

