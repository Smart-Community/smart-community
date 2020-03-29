package com.zc.controller;

import com.zc.client.FaultTypeClient;
import com.zc.util.CommonConstants;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小帅气
 * @create 2020-03-29-10:04
 */
@RestController
public class FaultTypeController {

    @Resource
    private FaultTypeClient faultTypeClient;

    @RequestMapping("/user/faulttype/query")
    public Object queryFaultType() {
        return faultTypeClient.queryFaultType();
    }

    @PostMapping("/admin/faulttype/add")
    public Object createFaultType(@RequestParam("name") String name) {
        return faultTypeClient.addFaultType(name);
    }

}
