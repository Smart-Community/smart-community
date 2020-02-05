package com.zc.dto;

import com.zc.pojo.PowerUrl;
import lombok.Data;

import java.util.List;

/**
 * @author 小帅气
 * @create 2020-02-05-12:52
 */
@Data
public class PermissionDTO {
    private  long roleId;
    private List<PowerUrl> powUrlList;
}
