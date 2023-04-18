package com.cloud.office.customer.busi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.office.customer.busi.service_im.dto.MessageListDto;
import com.cloud.office.customer.busi.service_im.entity.Message;

import java.util.List;

/**
 */
public interface MessageService extends IService<Message> {

    /**
     * 获取消息数据
     *
     * @param messageListDto 消息查询条件
     * @return
     */
    List<Message> findMessageList(MessageListDto messageListDto);
}
