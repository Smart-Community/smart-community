package com.zc.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "notification_message")
@Accessors(chain = true)
public class NotificationMessage implements Serializable {
    @Id
    @Column(name = "notification_message_id")
    private long notificationMessageId;
    @Column(name = "notification_message_admin_id")
    private long notificationMessageAdminId;
    @Column(name = "notification_message_type_id")
    private int notificationMessageTypeId;
    @Column(name = "notification_message_top")
    private String notificationMessageTop;
    @Column(name = "notification_message_describe")
    private String notificationMessageDescribe;
    @Column(name = "notification_message_state")
    private int notificationMessageState;
    @Column(name = "notification_message_release_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date notificationMessageReleaseTime;
    @Column(name = "notification_message_release_time_unix")
    private long notificationMessageReleaseTimeUnix;


}
