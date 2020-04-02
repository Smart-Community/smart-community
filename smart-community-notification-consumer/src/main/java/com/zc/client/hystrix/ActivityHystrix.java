package com.zc.client.hystrix;

import com.zc.client.ActivityClient;
import com.zc.vo.LayuiVO;
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
    public Map<String, Object> createNewActivity(Long releaseId, String name, String desc, Integer number, short limit, Date startTime, Date joinTime) {
        return null;
    }

    @Override
    public Map<String, Object> updateAcitvity(Long id) {
        return null;
    }

    @Override
    public LayuiVO queryActivy(String name, Integer status, Integer pageIndex, Integer pageSize) {
        return null;
    }
}
