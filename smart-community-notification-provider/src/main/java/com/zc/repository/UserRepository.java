package com.zc.repository;

import com.zc.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 小帅气
 * @create 2020-02-05-17:29
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    @Query(value = "select t from User t where t.userPhone=:userPhone and t.userState = 0")
    User findByUserPhone(@Param("userPhone") String userPhone);
    @Query(value = "select t from User t where t.userLogin=:userLogin and t.userState = 0")
    User findByUserLogin(@Param("userLogin")String userLogin);
    User findByUserId(long userId);
}
