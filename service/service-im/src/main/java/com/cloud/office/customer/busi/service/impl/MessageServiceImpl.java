package com.cloud.office.customer.busi.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.office.customer.busi.mapper.MessageMapper;
import com.cloud.office.customer.busi.service.MessageService;
import com.cloud.office.customer.busi.service_im.dto.MessageListDto;
import com.cloud.office.customer.busi.service_im.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
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

    @Override
    public Integer deleteMessage(Integer messageId) {
        int i = baseMapper.deleteById(messageId);
        return i;
    }
}
