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

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "activity_information")
@Accessors(chain = true)
public class ActivityInformation implements Serializable {

    @Id
    @Column(name = "activity_information_id")
    private long activityInformationId;

    @Column(name = "activity_information_name")
    private String activityInformationName;

    @Column(name = "activity_information_describe")
    private String activityInformationDescribe;

    @Column(name = "activity_information_releaser_id")
    private long activityInformationReleaserId;

    @Column(name = "activity_information_release_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activityInformationReleaseTime;

    @Column(name = "activity_information_start_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activityInformationStartTime;

    @Column(name = "activity_information_number")
    private long activityInformationNumber;

    @Column(name = "activity_information_surplus_number")
    private long activityInformationSurplusNumber;

    @Column(name = "activity_information_limit")
    private short activityInformationLimit;


}
