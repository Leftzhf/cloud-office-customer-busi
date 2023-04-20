package com.cloud.office.customer.busi.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.office.customer.busi.enums.MessageStatusEnum;
import com.cloud.office.customer.busi.mapper.MessageMapper;
import com.cloud.office.customer.busi.service.MessageService;
import com.cloud.office.customer.busi.service_im.dto.MessageListDto;
import com.cloud.office.customer.busi.service_im.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Autowired
    private MessageMapper messageMapper;
    /**
     * 获取消息分页数据
     *
     * @param messageListDto 消息分页查询条件
     * @return
     */
    @Override
    public List<Message> findMessageList(MessageListDto messageListDto) {
        //拉取消息数据
        List<Message> messages = baseMapper.selectMessageList(messageListDto);
        //把对方发送的消息状态更新为已读
       return makeReaded(messages,messageListDto.getContactUserId());
    }

    @Override
    public Integer deleteMessage(Integer messageId) {
        int i = baseMapper.deleteById(messageId);
        return i;
    }
    private List<Message> makeReaded(List<Message> messageList,Integer toUserId){
        messageList.stream().forEach(item->{
            if(item.getFromUserId().equals(toUserId)) {
                item.setStatus(MessageStatusEnum.READ);
            }
        });
        //批量更新
        saveOrUpdateBatch(messageList);
        return messageList;
    }
}
