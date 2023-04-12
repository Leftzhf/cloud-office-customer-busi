package com.cloud.office.customer.busi.controller;

import com.alibaba.fastjson.JSON;
import com.cloud.office.customer.busi.common.util.LocalJsonUtils;
import com.cloud.office.customer.busi.common.vo.ResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/common")
public class CommonController {

    /**
     * 获取省市数据
     *
     * @return
     */
    @GetMapping("/city")
    public ResultVo getProvinceCityList() {
        String cityJson = LocalJsonUtils.loadLocalJson("city.json");
        return ResultVo.success(JSON.parseArray(cityJson));
    }

    /**
     * 获取行业类型数据
     *
     * @return
     */
    @GetMapping("/industry")
    public ResultVo getIndustryList() {
        String cityJson = LocalJsonUtils.loadLocalJson("industry.json");
        return ResultVo.success(JSON.parseArray(cityJson));
    }
}
