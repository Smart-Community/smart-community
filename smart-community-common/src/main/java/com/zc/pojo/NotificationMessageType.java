package com.zc.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "notification_message_type")
@Accessors(chain = true)
public class NotificationMessageType implements Serializable {
    @Id
    @Column(name = "notification_message_type_type_id")
    private int notificationMessageTypeTypeId;
    @Column(name = "notification_message_type_type_name")
    private String notificationMessageTypeTypeName;


}
