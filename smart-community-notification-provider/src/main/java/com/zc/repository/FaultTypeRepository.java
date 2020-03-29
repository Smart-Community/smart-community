package com.zc.repository;

import com.zc.pojo.FaultType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author 小帅气
 * @create 2020-03-29-9:55
 */
@Repository
public interface FaultTypeRepository extends JpaRepository<FaultType, Integer>, JpaSpecificationExecutor<FaultType> {
}
