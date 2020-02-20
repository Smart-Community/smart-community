package com.zc.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "activity_registration")
@Accessors(chain = true)
public class ActivityRegistration implements Serializable {
    @Id
    @Column(name = "activity_registration_id")
    private long activityRegistrationId;
    @Column(name = "activity_registration_user_id")
    private long activityRegistrationUserId;
    @Column(name = "activity_registration_time")
    @JsonFormat(pattern = "activity_registration_time")
    private Date activityRegistrationTime;
    @Column(name = "activity_information_id")
    private long activityInformationId;
    @Column(name = "activity_registration_status")
    private int status;

}
