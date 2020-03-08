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
@Table(name = "admin")
@Accessors(chain = true)
public class Admin implements Serializable {
    @Id
    @Column(name = "admin_id")
    private long adminId;
    @Column(name = "admin_name")
    private String adminName;
    @Column(name = "admin_login")
    private String adminLogin;
    @Column(name = "admin_password")
    private String adminPassword;
    @Column(name = "admin_role_id")
    private int adminRoleId;
    @Column(name = "admin_last_login_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date adminLastLoginTime;
    @Column(name = "admin_state")
    private short adminState;


}
