package com.cloud.office.customer.busi.controller;

import com.alibaba.fastjson.JSON;
import com.cloud.office.customer.busi.service.ITbLeaveInfoService;
import com.cloud.office.customer.busi.service_openportal.domain.dto.LeaveInfoDto;
import com.cloud.office.customer.busi.service_openportal.domain.entity.TbLeaveInfo;
import com.cloud.office.customer.busi.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/23 00:02
 */
@Api(tags = "留言接口")
@Slf4j
@RestController
@RequestMapping("/leaveInfo")
public class LeaveInfoContoller {

    
    @Autowired
    private ITbLeaveInfoService leaveInfoService;


    @PostMapping("/list")
    @ApiOperation(value = "获取客户留言-分页")
    public ResultVo getFeedBackPageList(@RequestBody LeaveInfoDto leaveInfoDto) {
        log.info("获取访客留言-分页,{}", JSON.toJSONString(leaveInfoDto));
        return ResultVo.success(leaveInfoService.getLeaveInfoPageList(leaveInfoDto));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增客户留言")
    public ResultVo addFeedBack(@RequestBody TbLeaveInfo leaveInfo) {
        log.info("新增访客留言,{}", JSON.toJSONString(leaveInfo));
        return ResultVo.success(leaveInfoService.addLeaveInfo(leaveInfo));
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改客户留言")
    public ResultVo updateFeedBack(@RequestBody TbLeaveInfo leaveInfo) {
        log.info("修改访客留言,{}", JSON.toJSONString(leaveInfo));
        return ResultVo.success(leaveInfoService.updateLeaveInfo(leaveInfo));
    }

    @DeleteMapping("/delete/{leaveInfoId}")
    @ApiOperation(value = "删除客户留言")
    public ResultVo deleteFeedBack(@PathVariable Integer leaveInfoId) {
        log.info("删除访客留言,{}", JSON.toJSONString(leaveInfoId));
        return ResultVo.success(leaveInfoService.deleteLeaveInfo(leaveInfoId));
    }
}
