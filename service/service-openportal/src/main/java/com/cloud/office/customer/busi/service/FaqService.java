package com.cloud.office.customer.busi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.office.customer.busi.common.vo.PageVo;
import com.cloud.office.customer.busi.service_openportal.domain.dto.FaqPageDto;
import com.cloud.office.customer.busi.service_openportal.domain.entity.Faq;

/**
 * @author feng
 * @date 2019-06-03
 */
public interface FaqService extends IService<Faq> {

    PageVo<Faq> getFaqPageList(FaqPageDto faqPageDto);

}
