package com.zc.business.impl;

import com.zc.business.UserBusiness;
import com.zc.mapper.UserMapper;
import com.zc.pojo.HouseOwnership;
import com.zc.pojo.User;
import com.zc.repository.HouseOwnershipRepository;
import com.zc.repository.UserRepository;
import com.zc.vo.LayuiVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-02-05-17:54
 */
@Service
@CacheConfig(cacheNames = {"userLoginBusiness"})
public class UserBusinessImpl implements UserBusiness {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HouseOwnershipRepository houseOwnershipRepository;
    @Resource
    private UserMapper userMapper;

    @Override
    @Cacheable(key = "T(String).valueOf('phone').concat(#phone)")
    public User findByPhone(String phone) {
        return userRepository.findByUserPhone(phone);
    }

    @Override
    @Cacheable(key = "T(String).valueOf('name').concat(#loginName)")
    public User findByLoginName(String loginName) {
        return userRepository.findByUserLogin(loginName);
    }

    @Override
    @Cacheable(key = "T(String).valueOf('userId').concat(#userId)")
    public User findByUserId(long userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(key = "T(String).valueOf('phone').concat(#user.userPhone)"),
            @CacheEvict(key = "T(String).valueOf('name').concat(#user.userLogin)"),
            @CacheEvict(key = "T(String).valueOf('userId').concat(#user.userId)")
    })
    @Transactional
    public User save(User user) {
        System.out.println(userRepository);
        return userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public User addUser(User user) {
        user = userRepository.saveAndFlush(user);
        return user;
    }

    @Override
    public List<Map<String, Object>> findByHouseId(Long houseId) {
        return userMapper.findByHouseId(houseId);
    }

    @Override
    public Integer countByHouseId(Long houseId) {
        return userMapper.countByHouseId(houseId);
    }

    @Override
    public LayuiVO query(String userName, String phone, Integer state, Integer pageIndex, Integer pageSize) {
        LayuiVO layuiVO = new LayuiVO();
        Page<User> page = userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicatesList = new ArrayList<>();
            if (StringUtils.isNotBlank(userName)) {
                predicatesList.add(criteriaBuilder.like(root.get(userName).as(String.class), userName));
            }
            if (StringUtils.isNotBlank(phone)) {
                predicatesList.add(criteriaBuilder.equal(root.get("userPhone").as(String.class), phone));
            }
            if (state != null) {
                predicatesList.add(criteriaBuilder.equal(root.get("userState").as(Integer.class), state));
            }
            return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
        }, PageRequest.of(pageIndex - 1, pageSize, Sort.by(Sort.Direction.DESC, "userId")));
        layuiVO.setCount((int) page.getTotalElements());
        List<User> content = page.getContent();
        for (User user : content) {
            user.setUserPassword(null);
        }
        layuiVO.setData(content);
        layuiVO.setMsg("查询成功");
        layuiVO.setCode(0);
        return layuiVO;
    }

    @Override
    public User updateState(Long userId, Integer state) {
        User user = userRepository.findByUserId(userId);
        user.setUserState(state);
        return userRepository.saveAndFlush(user);
    }
}
