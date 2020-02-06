package com.zc.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "role")
@Accessors(chain = true)
public class User {
    @Id
    @Column(name = "user_id")
    private long userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_phone")
    private String userPhone;
    @Column(name = "user_login")
    private String userLogin;
    @Column(name = "user_password")
    private String userPassword;
    @Column(name = "user_role_id")
    private int userRoleId;
    @Column(name = "user_state")
    private short userState;
    @Column(name = "user_img_url")
    private String userImgUrl;
    @Column(name = "user_last_login_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date userLastLoginTime;
    @Transient
    private String token;

}
