package com.zc.service;

import com.zc.business.MenuBusiness;
import com.zc.util.CommonConstants;
import com.zc.vo.ResultWrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.stream.events.Comment;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-02-29-19:57
 */
@RestController
public class MenuService {

    @Autowired
    private MenuBusiness menuBusiness;

    @RequestMapping("/v1.0/menu/menulist/get")
    public Map<String, Object> getMenuListByRoleId(@RequestParam("roleId") Long roleId) {
        Map<String, Object> map = new HashMap<>();
        map.put(CommonConstants.RESP_CODE, CommonConstants.SUCCESS);
        map.put(CommonConstants.RESP_MESSAGE, "查询成功");
        map.put("data", menuBusiness.getMenuListByRoleId(roleId));
        return map;
    }

}
