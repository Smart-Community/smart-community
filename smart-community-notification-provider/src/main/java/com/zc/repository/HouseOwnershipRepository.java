package com.zc.repository;

import com.zc.pojo.HouseOwnership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author 小帅气
 * @create 2020-02-07-15:35
 */
@Repository
public interface HouseOwnershipRepository extends JpaRepository<HouseOwnership,Long>, JpaSpecificationExecutor<HouseOwnership> {

    @Query("select count(ho) from HouseOwnership  ho where ho.houseOwnershipUserId=?1")
    int queryCountByUserId(Long userId);
}
