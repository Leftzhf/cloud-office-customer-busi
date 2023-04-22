package com.cloud.office.customer.busi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.office.customer.busi.service_openportal.domain.dto.FaqPageDto;
import com.cloud.office.customer.busi.service_openportal.domain.entity.Faq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FaqMapper extends BaseMapper<Faq> {

    IPage<Faq> selectPageList(FaqPageDto faqPageDto);
}
