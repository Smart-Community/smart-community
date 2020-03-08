package com.zc.business.impl;

import com.zc.business.UserBusiness;
import com.zc.pojo.HouseOwnership;
import com.zc.pojo.User;
import com.zc.repository.HouseOwnershipRepository;
import com.zc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Cacheable(key = "#p0")
    public User findByPhone(String phone) {
        return userRepository.findByUserPhone(phone);
    }

    @Override
    @Cacheable(key = "#p0")
    public User findByLoginName(String loginName) {
        return userRepository.findByUserLogin(loginName);
    }

    @Override
    @Cacheable(key = "#p0")
    public User findByUserId(long userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(key = "#user.userId"),
            @CacheEvict(key = "#user.userPhone"),
            @CacheEvict(key = "#user.userLogin")
    })
    @Transactional
    public User save(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public User addUser(User user, long houseId, int isOwner) {
        user = userRepository.save(user);
        HouseOwnership houseOwnership = new HouseOwnership();
        houseOwnership.setHouseOwnershipUserId(user.getUserId());
        houseOwnership.setHouseOwnershipHouseId(houseId);
        houseOwnership.setHouseOwnershipIsOwner(isOwner);
        houseOwnershipRepository.save(houseOwnership);
        return user;
    }

}
