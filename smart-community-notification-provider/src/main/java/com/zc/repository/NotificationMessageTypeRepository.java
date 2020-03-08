package com.zc.repository;

import com.zc.pojo.NotificationMessageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author 小帅气
 * @create 2020-03-08-22:37
 */
@Repository
public interface NotificationMessageTypeRepository extends JpaRepository<NotificationMessageType, Integer>,
        JpaSpecificationExecutor<NotificationMessageType> {
}
