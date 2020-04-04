package com.zc.client.hystrix;

import com.zc.client.ActivityClient;
import com.zc.util.CommonConstants;
import com.zc.vo.LayuiVO;
import com.zc.vo.ResultWrap;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-04-01-21:57
 */
@Service
public class ActivityHystrix implements ActivityClient {
    @Override
    public Map<String, Object> createNewActivity(Long releaseId, String name, String desc, Integer number,
                                                 short limit, Date startTime, Date joinTime) {
        return ResultWrap.init(CommonConstants.FALIED, "系统异常");
    }

    @Override
    public Map<String, Object> updateAcitvity(Long id) {
        return ResultWrap.init(CommonConstants.FALIED, "系统异常");
    }

    @Override
    public Map<String, Object> endAcitvity(Long id) {
        return ResultWrap.init(CommonConstants.FALIED, "系统异常");
    }

    @Override
    public LayuiVO queryActivy(String name, Integer status, Integer pageIndex, Integer pageSize) {
        return null;
    }

    @Override
    public LayuiVO queryByUserActivy(String name, Integer status, Long userId, Integer pageIndex, Integer pageSize) {
        return null;
    }

    @Override
    public Object joinBy(Long userId, Long id) {
        return ResultWrap.init(CommonConstants.FALIED, "系统异常");
    }

    @Override
    public LayuiVO queryActivityPerson(Long id, Integer pageSize, Integer pageIndex) {
        return null;
    }
}
