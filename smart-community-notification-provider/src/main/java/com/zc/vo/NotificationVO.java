package com.zc.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 小帅气
 * @create 2020-03-06-9:39
 */
@Data
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class NotificationVO implements Serializable {
    //消息id
    private long id;
    //发布人id
    private long adminId;
    //发布人名称
    private String adminName;
    //类型id
    private long typeId;
    //类型名称
    private String typeName;
    //标题
    private String top;
    //内容
    private String desc;
    //状态
    private int state;
    //发布时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date releaseTime;

}
