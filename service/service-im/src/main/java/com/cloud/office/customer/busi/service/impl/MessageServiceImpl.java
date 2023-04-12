package com.cloud.office.customer.busi.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * @author feng
 * @date 2019-06-08
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    /**
     * 获取消息分页数据
     *
     * @param messageListDto 消息分页查询条件
     * @return
     */
    @Override
    public List<Message> findMessageList(MessageListDto messageListDto) {
        return baseMapper.selectMessageList(messageListDto);
    }
}
