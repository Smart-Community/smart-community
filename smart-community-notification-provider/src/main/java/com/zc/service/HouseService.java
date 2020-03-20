package com.zc.service;

import com.xuxueli.poi.excel.ExcelImportUtil;
import com.zc.business.HouseBusiness;
import com.zc.dto.HouseDTO;
import com.zc.pojo.House;
import com.zc.util.CommonConstants;
import com.zc.vo.LayuiVO;
import com.zc.vo.ResultWrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-02-07-22:51
 */
@RestController
public class HouseService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private HouseBusiness houseBusiness;


    //todo 导入数据
    @PostMapping("/admin/houseinfo/import")
    public Map<String, Object> importHouseInfo(@RequestParam("file") MultipartFile file) {
        List<Object> list = ExcelImportUtil.importExcel("", HouseDTO.class);
        return null;
    }

    @PostMapping("/v1.0/house/query/page")
    public LayuiVO queryHouseInfo(@RequestParam(value = "tungId", required = false) Integer tungId,
                                  @RequestParam(value = "unitId", required = false) Integer unitId,
                                  @RequestParam(value = "number", required = false) Integer number,
                                  @RequestParam(value = "maxArea", required = false) BigDecimal maxArea,
                                  @RequestParam(value = "minArea", required = false) BigDecimal minArea,
                                  @RequestParam(value = "state", required = false) Integer state,
                                  @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageIndex,
                                  @RequestParam(value = "limit", required = false, defaultValue = "20") Integer pageSize) {

        LayuiVO layuiVO = new LayuiVO();
        Page<House> houses = houseBusiness.queryByPage(tungId, unitId, number, maxArea, minArea, state, pageIndex,
                pageSize);
        layuiVO.setData(houses.getContent());
        layuiVO.setCount((int) houses.getTotalElements());
        layuiVO.setMsg("查询成功");
        layuiVO.setCode(000000);
        return layuiVO;
    }

    @PostMapping("/v1.0/house/query/by/{userId}")
    public LayuiVO queryHouseByUserId(@PathVariable("userId") Long userId,
                                      @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
                                      @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        LayuiVO layuiVO = new LayuiVO();
        layuiVO.setData(houseBusiness.queryByUserId(userId, pageIndex, pageSize));
        layuiVO.setCount(houseBusiness.totalByUserId(userId));
        layuiVO.setMsg("查询成功");
        layuiVO.setCode(000000);
        return layuiVO;
    }

    @PostMapping("/v1.0/house/pay/managent/{houseId}")
    public Map<String,Object> payManagent(@PathVariable("houseId")Long houseId,
                              @RequestParam("adminId")Long adminId,
                              @RequestParam("money")BigDecimal money){
        houseBusiness.payManager(houseId,adminId,money);
        return ResultWrap.init(CommonConstants.SUCCESS,"缴费成功");
    }

}
