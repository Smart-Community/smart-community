package com.zc.business;

import com.zc.pojo.User;
import com.zc.vo.LayuiVO;

import java.util.List;
import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-02-05-17:54
 */
public interface UserBusiness {

    User findByPhone(String phone);

    User findByLoginName(String loginName);

    User findByUserId(long userId);

    User save(User user);

    User addUser(User user);

    List<Map<String,Object>> findByHouseId(Long houseId);

    Integer countByHouseId(Long houseId);

    LayuiVO query(String userName,String phone,Integer state,Integer pageIndex,Integer pageSize);

    User updateState(Long userId,Integer state);
}
