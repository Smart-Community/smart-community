package com.zc.repository;

import com.zc.pojo.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 小帅气
 * @create 2020-02-29-19:57
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer>, JpaSpecificationExecutor<Menu> {

    @Query("select m from Menu m where m.roleId = ?1")
    List<Menu> getMenuListByRoleId(Long roleId);
}
