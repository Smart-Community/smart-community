package com.zc.repository;

import com.zc.pojo.ActivityInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 小帅气
 * @create 2020-02-19-20:15
 */
@Repository
public interface ActivityRepository extends JpaRepository<ActivityInformation,Long> , JpaSpecificationExecutor<ActivityInformation> {

    @Query("select ai from ActivityInformation ai where ai.status=?1")
    List<ActivityInformation> findListByState(int state);

}
