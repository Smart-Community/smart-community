package com.zc.repository;

import com.zc.pojo.ActivityRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author 小帅气
 * @create 2020-02-19-20:47
 */
@Repository
public interface ActivityRegistrationRepository extends JpaRepository<ActivityRegistration,Long>, JpaSpecificationExecutor<ActivityRegistration> {
}
