package com.zc.repository;

import com.zc.pojo.PropertyPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author 小帅气
 * @create 2020-03-15-16:13
 */
@Repository
public interface PropertyPaymentRepository extends JpaRepository<PropertyPayment, Long>,
        JpaSpecificationExecutor<PropertyPayment> {
}
