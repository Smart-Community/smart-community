package com.zc.repository;

import com.zc.pojo.FaultMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author 小帅气
 * @create 2020-02-07-22:22
 */
@Repository
public interface FaultMaintenanceRepository extends JpaRepository<FaultMaintenance, Long>,
        JpaSpecificationExecutor<FaultMaintenance> {
}
