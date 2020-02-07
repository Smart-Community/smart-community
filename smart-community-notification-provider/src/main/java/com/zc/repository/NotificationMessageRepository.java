package com.zc.repository;

import com.zc.pojo.NotificationMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author 小帅气
 * @create 2020-02-07-20:41
 */
@Repository
public interface NotificationMessageRepository extends JpaRepository<NotificationMessage,Long>, JpaSpecificationExecutor<NotificationMessage> {

}
