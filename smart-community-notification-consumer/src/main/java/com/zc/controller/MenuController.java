package com.zc.controller;

import com.alibaba.fastjson.JSONObject;
import com.zc.client.MenuClient;
import com.zc.pojo.User;
import com.zc.util.CommonConstants;
import com.zc.util.RedisUtil;
import com.zc.util.TokenUtil;
import com.zc.vo.ResultWrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-02-29-19:43
 */
@RestController
public class MenuController {
    @Value("${userinfo.key}")
    private String USER_INFO_KYE;




}
