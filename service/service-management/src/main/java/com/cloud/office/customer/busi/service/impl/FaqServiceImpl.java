package com.cloud.office.customer.busi.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.office.customer.busi.ServiceUsercenterClient;
import com.cloud.office.customer.busi.db.util.PageUtils;
import com.cloud.office.customer.busi.vo.PageVo;
import com.cloud.office.customer.busi.mapper.FaqMapper;
import com.cloud.office.customer.busi.service.FaqService;
import com.cloud.office.customer.busi.service_openportal.domain.dto.FaqPageDto;
import com.cloud.office.customer.busi.service_openportal.domain.entity.Faq;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author leftleft
 * @date 2023-06-03
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class FaqServiceImpl extends ServiceImpl<FaqMapper, Faq> implements FaqService {

    @Autowired
    private ServiceUsercenterClient usercenterClient;
    @Override
    public PageVo<Faq> getFaqPageList(FaqPageDto faqPageDto) {
        IPage<Faq> page = baseMapper.selectPageList(faqPageDto);
        page.getRecords().forEach(item->{
            User userById = usercenterClient.getUserById(item.getUserId());
            item.setUser(userById);
        });
        return PageUtils.getPageVo(page);
    }
}
