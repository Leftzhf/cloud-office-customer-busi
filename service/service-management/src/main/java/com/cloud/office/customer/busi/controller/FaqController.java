package com.cloud.office.customer.busi.controller;

import com.cloud.office.customer.busi.service.FaqService;
import com.cloud.office.customer.busi.service_management.dto.FaqPageDto;
import com.cloud.office.customer.busi.service_management.entity.Faq;
import com.cloud.office.customer.busi.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/faq")
@Api(tags = "常见问题接口",description = "常见问题接口")
public class FaqController {

    @Autowired
    private FaqService faqService;

    @PostMapping
    @ApiOperation(value = "新增问题")
    public ResultVo addFaq(@RequestBody Faq faq) {
        faqService.save(faq);
        return ResultVo.success();
    }

    @DeleteMapping("/{faqId}")
    @ApiOperation(value = "删除问题")
    public ResultVo deleteFaq(@PathVariable Integer faqId) {
        faqService.removeById(faqId);
        return ResultVo.success();
    }

    @ApiOperation(value = "更新问题")
    @PutMapping
    public ResultVo updateFaq(@RequestBody Faq faq) {
        faqService.updateById(faq);
        return ResultVo.success();
    }

    @GetMapping("/{faqId}")
    @ApiOperation(value = "根据id获取问题")
    public ResultVo getFaq(@PathVariable Integer faqId) {
        return ResultVo.success(faqService.getById(faqId));
    }

    @PostMapping("/list")
    @ApiOperation(value = "查询问题-分页")
    public ResultVo getFaqList(@RequestBody FaqPageDto faqPageDto) {
        return ResultVo.success(faqService.getFaqPageList(faqPageDto));
    }

}
