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
@Table(name = "fault_maintenance")
@Accessors(chain = true)
public class FaultMaintenance implements Serializable {
    @Id
    @Column(name = "fault_maintenance_id")
    private long faultMaintenanceId;
    @Column(name = "fault_maintenance_user_id")
    private long faultMaintenanceUserId;
    @Column(name = "fault_maintenance_fault_type_id")
    private long faultMaintenanceFaultTypeId;
    @Column(name = "fault_maintenance_describe")
    private String faultMaintenanceDescribe;
    @Column(name = "fault_maintenance_addrss")
    private String faultMaintenanceAddrss;
    @Column(name = "fault_maintenance_phone")
    private String faultMaintenancePhone;
    @Column(name = "fault_maintenance_state")
    private int faultMaintenanceState;
    @Column(name = "fault_maintenance_release_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date faultMaintenanceReleaseTime;
    @Column(name = "fault_maintenance_release_time_unix")
    private long faultMaintenanceReleaseTimeUnix;


}
