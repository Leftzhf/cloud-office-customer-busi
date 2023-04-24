package com.cloud.office.customer.busi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.office.customer.busi.service_im.dto.ConversationDTO;
import com.cloud.office.customer.busi.service_im.entity.Conversation;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;

import java.util.List;

/**
 */
public interface ConversationService extends IService<Conversation> {

    /**
     * 查询会话列表
     *
     * @param userId 用户编号
     * @return
     */
    List<Conversation> selectListByUserId(Integer userId);
    List<Conversation> selectListByUserIdRes(Integer userId);

    Boolean createConversation(ConversationDTO conversationDTO);
    List<User> getListOnlineServer();
    List<User> getListOnlineServerByTeamId(Integer teamId);

    List<User> getListOnlineCustomer();

}
