package com.cloud.office.customer.busi.controller;

import com.alibaba.fastjson.JSON;
import com.cloud.office.customer.busi.service.ITbFeedbackService;
import com.cloud.office.customer.busi.service_openportal.domain.dto.FeedBackDto;
import com.cloud.office.customer.busi.service_openportal.domain.entity.TbFeedback;
import com.cloud.office.customer.busi.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/18 17:08
 */
@Api(tags = "客户反馈接口")
@Slf4j
@RestController
@RequestMapping("/feedback")
public class FeedBackController {
    @Autowired
    private ITbFeedbackService feedbackService;

    @PostMapping("/list")
    @ApiOperation(value = "获取客户反馈-分页")
    public ResultVo getFeedBackPageList(@RequestBody FeedBackDto feedBackQuery) {
        log.info("获取访客反馈-分页,{}", JSON.toJSONString(feedBackQuery));
        return ResultVo.success(feedbackService.getFeedBackPageList(feedBackQuery));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增客户反馈")
    public ResultVo addFeedBack(@RequestBody TbFeedback feedBack) {
        log.info("插入访客反馈,{}", JSON.toJSONString(feedBack));
        return ResultVo.success(feedbackService.addFeedBack(feedBack));
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改客户反馈")
    public ResultVo updateFeedBack(@RequestBody TbFeedback feedBack) {
        log.info("修改访客反馈,{}", JSON.toJSONString(feedBack));
        return ResultVo.success(feedbackService.updateFeedBack(feedBack));
    }

    @DeleteMapping("/delete/{feedBcakId}")
    @ApiOperation(value = "删除客户反馈")
    public ResultVo deleteFeedBack(@PathVariable Integer feedBcakId) {
        log.info("删除访客反馈,{}", JSON.toJSONString(feedBcakId));
        return ResultVo.success(feedbackService.deleteFeedBack(feedBcakId));
    }

}
