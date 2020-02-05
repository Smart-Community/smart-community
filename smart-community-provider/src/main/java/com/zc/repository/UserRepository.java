package com.zc.repository;

import com.zc.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 小帅气
 * @create 2020-02-05-17:29
 */
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

}
