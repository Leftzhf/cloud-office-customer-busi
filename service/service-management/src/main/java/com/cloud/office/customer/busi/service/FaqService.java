package com.cloud.office.customer.busi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.office.customer.busi.vo.PageVo;
import com.cloud.office.customer.busi.service_management.dto.FaqPageDto;
import com.cloud.office.customer.busi.service_management.entity.Faq;

/**
 * @author leftleft
 * @date 2023-06-03
 */
public interface FaqService extends IService<Faq> {

    PageVo<Faq> getFaqPageList(FaqPageDto faqPageDto);

}
