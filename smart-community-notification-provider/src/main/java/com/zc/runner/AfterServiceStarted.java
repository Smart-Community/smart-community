package com.zc.runner;

import com.google.common.base.Joiner;
import com.zc.dto.PermissionDTO;
import com.zc.mapper.PermissionsMapper;
import com.zc.pojo.PowerUrl;
import com.zc.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-02-05-11:15
 * 装载权限控制信息
 */
@Component
@Order(value = 1)
public class AfterServiceStarted implements ApplicationRunner {
    @Resource
    private PermissionsMapper permissionsMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${permission.key}")
    private String PERMISSION_KEY;

    @Override
    public void run(ApplicationArguments args) {
        List<PermissionDTO> list = permissionsMapper.permissionsAll();
        Map<String, String> map = new HashMap<>();
        for (PermissionDTO permissionDTO : list) {
            List<String> urlList = new ArrayList<>();
            for (PowerUrl powerUrl : permissionDTO.getPowUrlList()) {
                urlList.add(powerUrl.getPowerUrlPrefix());
            }
            map.put(permissionDTO.getRoleId() + "", Joiner.on("---").join(urlList));
        }
        stringRedisTemplate.opsForHash().putAll(PERMISSION_KEY, map);
    }
}
