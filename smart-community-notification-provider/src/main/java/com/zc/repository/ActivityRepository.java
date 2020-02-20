package com.zc.repository;

import com.zc.pojo.ActivityInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author 小帅气
 * @create 2020-02-19-20:15
 */
@Repository
public interface ActivityRepository extends JpaRepository<ActivityInformation,Long> , JpaSpecificationExecutor<ActivityInformation> {

}
