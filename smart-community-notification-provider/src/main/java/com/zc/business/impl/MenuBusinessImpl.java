package com.zc.business.impl;

import com.zc.business.MenuBusiness;
import com.zc.pojo.Menu;
import com.zc.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 小帅气
 * @create 2020-02-29-19:59
 */
@Service
public class MenuBusinessImpl implements MenuBusiness {
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> getMenuListByRoleId(Long roleId) {
        return menuRepository.getMenuListByRoleId(roleId);
    }
}
