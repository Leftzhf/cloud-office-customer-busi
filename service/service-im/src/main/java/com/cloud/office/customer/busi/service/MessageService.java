package com.cloud.office.customer.busi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.office.customer.busi.service_im.dto.MessageListDto;
import com.cloud.office.customer.busi.service_im.dto.MessageListPageDTO;
import com.cloud.office.customer.busi.service_im.dto.RecallMessageDto;
import com.cloud.office.customer.busi.service_im.entity.Message;
import com.cloud.office.customer.busi.vo.PageVo;

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

    Boolean RecallMessage(RecallMessageDto messageListDto);

    PageVo<Message> findMessagePage(MessageListPageDTO messageListDto);

    Boolean deleteMessageById(Integer id);

}
