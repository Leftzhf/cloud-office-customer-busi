package com.cloud.office.customer.busi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.office.customer.busi.mapper.ConversationMapper;
import com.cloud.office.customer.busi.service.ConversationService;
import com.cloud.office.customer.busi.service_im.entity.Conversation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author feng
 * @date 2019-06-08
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ConversationServiceImpl extends ServiceImpl<ConversationMapper, Conversation> implements ConversationService {

    /**
     * 查询会话列表
     *
     * @param userId 用户编号
     * @return
     */
    @Override
    public List<Conversation> selectListByUserId(Integer userId) {
        return baseMapper.selectListByUserId(userId);
    }
}
