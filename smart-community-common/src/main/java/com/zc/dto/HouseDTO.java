package com.zc.dto;

import com.xuxueli.poi.excel.annotation.ExcelField;
import com.xuxueli.poi.excel.annotation.ExcelSheet;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 小帅气
 * @create 2020-02-07-22:31
 */
@Data
@ExcelSheet(name = "sheet1")
public class HouseDTO implements Serializable {
    @ExcelField(name = "楼号")
    private int houseTungId;
    @ExcelField(name = "单元号")
    private int houseUnitId;
    @ExcelField(name = "门牌号")
    private int houseNumber;
    @ExcelField(name = "面积")
    private BigDecimal houseArea;
    @ExcelField(name = "状态")
    private int houseState;
}
