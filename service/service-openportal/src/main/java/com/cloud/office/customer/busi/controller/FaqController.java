package com.cloud.office.customer.busi.controller;

import com.cloud.office.customer.busi.common.vo.ResultVo;
import com.cloud.office.customer.busi.service.FaqService;
import com.cloud.office.customer.busi.service_openportal.domain.dto.FaqPageDto;
import com.cloud.office.customer.busi.service_openportal.domain.entity.Faq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/faq")
public class FaqController {

    @Autowired
    private FaqService faqService;

    @PostMapping
    public ResultVo addFaq(@RequestBody Faq faq) {
        faqService.save(faq);
        return ResultVo.success();
    }

    @DeleteMapping("/{faqId}")
    public ResultVo deleteFaq(@PathVariable Integer faqId) {
        faqService.removeById(faqId);
        return ResultVo.success();
    }

    @PutMapping
    public ResultVo updateFaq(@RequestBody Faq faq) {
        faqService.updateById(faq);
        return ResultVo.success();
    }

    @GetMapping("/{faqId}")
    public ResultVo getFaq(@PathVariable Integer faqId) {
        return ResultVo.success(faqService.getById(faqId));
    }

    @PostMapping("/list")
    public ResultVo getFaqList(@RequestBody FaqPageDto faqPageDto) {
        return ResultVo.success(faqService.getFaqPageList(faqPageDto));
    }

}
