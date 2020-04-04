package com.zc.repository;

import com.zc.pojo.ActivityRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author 小帅气
 * @create 2020-02-19-20:47
 */
@Repository
public interface ActivityRegistrationRepository extends JpaRepository<ActivityRegistration, Long>,
        JpaSpecificationExecutor<ActivityRegistration> {

    @Query("select count(1) from ActivityRegistration a where a.activityRegistrationUserId=?1 and a" +
            ".activityInformationId=?2")
    int countByUserIdAndActivityId(Long userId, Long activityId);
}
