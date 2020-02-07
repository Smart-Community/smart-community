package com.zc.service;

import com.xuxueli.poi.excel.ExcelImportUtil;
import com.zc.dto.HouseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/admin/houseinfo/import")
    public Map<String, Object> importHouseInfo(@RequestParam("file")MultipartFile file){



        List<Object> list = ExcelImportUtil.importExcel("", HouseDTO.class);
        return null;
    }
}
