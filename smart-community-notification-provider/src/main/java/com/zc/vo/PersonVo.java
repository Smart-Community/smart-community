package com.zc.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 小帅气
 * @create 2020-04-04-17:49
 */
@Data
public class PersonVo implements Serializable {

    private Long userId;

    private String userName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date joinTime;

    private String activityName;

    private String phone;

}
