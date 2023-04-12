package com.cloud.office.customer.busi.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.office.customer.busi.common.db.util.PageUtils;
import com.cloud.office.customer.busi.common.vo.PageVo;
import com.cloud.office.customer.busi.mapper.FaqMapper;
import com.cloud.office.customer.busi.service.FaqService;
import com.cloud.office.customer.busi.service_openportal.domain.dto.FaqPageDto;
import com.cloud.office.customer.busi.service_openportal.domain.entity.Faq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author feng
 * @date 2019-06-03
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class FaqServiceImpl extends ServiceImpl<FaqMapper, Faq> implements FaqService {

    @Override
    public PageVo<Faq> getFaqPageList(FaqPageDto faqPageDto) {
        IPage<Faq> page = baseMapper.selectPageList(faqPageDto);
        return PageUtils.getPageVo(page);
    }
}
