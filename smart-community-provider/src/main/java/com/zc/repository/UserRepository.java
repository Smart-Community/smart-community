package com.zc.repository;

import com.zc.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author 小帅气
 * @create 2020-02-05-17:29
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
//    @Query(value = "select t from User t where t.userPhone=:userPhone")
    User findByUserPhone(@Param("userPhone") String userPhone);
//    @Query(value = "select t from User t where t.userLogin=:userLogin")
    User findByUserLogin(@Param("userLogin")String userLogin);
}
