package com.zc.business;

import com.zc.pojo.Menu;

import java.util.List;

/**
 * @author 小帅气
 * @create 2020-02-29-19:58
 */
public interface MenuBusiness {
    List<Menu> getMenuListByRoleId(Long roleId);
}
