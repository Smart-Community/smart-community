package com.zc.business.impl;

import com.zc.business.ActivityBusiness;
import com.zc.enums.AcitveConstants;
import com.zc.mapper.ActivityMapper;
import com.zc.pojo.ActivityInformation;
import com.zc.pojo.ActivityRegistration;
import com.zc.repository.ActivityRegistrationRepository;
import com.zc.repository.ActivityRepository;
import com.zc.util.RedisUtil;
import com.zc.vo.ActiveVO;
import com.zc.vo.LayuiVO;
import com.zc.vo.PersonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.StringRedisTemplate;
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

import static com.zc.enums.AcitveConstants.JOIN_KEY;

/**
 * @author 小帅气
 * @create 2020-02-19-20:14
 */
@Service
public class ActivityBuinessImpl implements ActivityBusiness {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityRegistrationRepository activityRegistrationRepository;

    @Resource
    private ActivityMapper activityMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createNewActivity(Long userId, String name, String desc, Date startTime,
                                  Date joinTime,
                                  Integer sum, short limit) {
        ActivityInformation activityInformation = new ActivityInformation();
        activityInformation.setActivityInformationDescribe(desc)
                .setActivityInformationLimit(limit)
                .setActivityInformationSurplusNumber(sum)
                .setActivityInformationName(name)
                .setActivityInformationStartTime(startTime)
                .setJoinTime(joinTime)
                .setActivityInformationNumber(sum)
                .setStatus(0)
                .setJoin(0)
                .setActivityInformationReleaseTime(new Date())
                .setActivityInformationReleaserId(userId);
        if (limit == 0) {
            activityInformation.setActivityInformationNumber(0);
        }
        ActivityInformation activityInformation1 = activityRepository.saveAndFlush(activityInformation);

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
        activityRegistrationRepository.saveAndFlush(activityRegistration);
        activityMapper.updateJoinNum(activityId, 1);
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
        // 更改redis
        stringRedisTemplate.opsForValue().increment(AcitveConstants.ACTIVE_KEY.getKey() + activityId + AcitveConstants.NUM_KEY.getKey(), -1L);
    }

    @Override
    public LayuiVO queryActiveList(String name, Integer status, Integer pageIndex, Integer pageSize) {
        List<ActiveVO> activeVOS = activityMapper.queryActiveList(name, status, (pageIndex - 1) * pageSize, pageSize);
        LayuiVO layuiVO = new LayuiVO();
        layuiVO.setData(activeVOS);
        layuiVO.setCount(activityMapper.queryActiveCount(name, status));
        return layuiVO;
    }

    @Override
    public ActivityInformation findById(Long id) {
        return activityRepository.findById(id).get();
    }

    @Override
    public List<ActivityInformation> findListByState(Integer state) {
        return activityRepository.findListByState(state);
    }

    @Override
    public LayuiVO queryUserActiveList(String name, Integer status, Long userId, Integer pageIndex, Integer pageSize) {
        if (status ==null || status != 6) {
            return queryActiveList(name, status, pageIndex, pageSize);
        }
        List<ActiveVO> activeVOS = activityMapper.queryUserActiveList(name, status, userId,(pageIndex - 1) * pageSize, pageSize);
        LayuiVO layuiVO = new LayuiVO();
        layuiVO.setData(activeVOS);
        layuiVO.setCount(activityMapper.queryCountUser(name, status,userId));
        return layuiVO;
    }

    @Override
    public int queryByUserIdAndActivityId(Long userId, Long id) {
        return activityRegistrationRepository.countByUserIdAndActivityId(userId,id);
    }

    @Override
    public LayuiVO queryActivityPerson(Long id,Integer pageIndex,Integer pageSize) {
        List<PersonVo> personVos = activityMapper.queryActivityPerson(id,(pageIndex - 1) * pageSize, pageSize);
        int count = activityMapper.countActivityPerson(id);
        LayuiVO layuiVO = new LayuiVO();
        layuiVO.setData(personVos);
        layuiVO.setCount(count);
        return layuiVO;
    }
}

