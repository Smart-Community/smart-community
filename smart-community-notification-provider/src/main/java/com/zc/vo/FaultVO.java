package com.zc.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 小帅气
 * @create 2020-03-29-14:34
 */
@Data
public class FaultVO implements Serializable {
    private long id;
    private int state;
    private String type;
    private String addr;
    private String userName;
    private String desc;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date releaseTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
