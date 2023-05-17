package com.cloud.office.customer.busi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.office.customer.busi.service_im.dto.ConversationDTO;
import com.cloud.office.customer.busi.service_im.entity.Conversation;
import com.cloud.office.customer.busi.service_im.query.TimeQuery;
import com.cloud.office.customer.busi.service_im.vo.ConverSationStateBarVO;
import com.cloud.office.customer.busi.service_im.vo.ConversationDistributionVO;
import com.cloud.office.customer.busi.service_im.vo.ConversationStateVO;
import com.cloud.office.customer.busi.service_im.vo.OnlineSessionVO;
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

    ConversationDistributionVO getConversationDistributionVO(ConversationDTO conversationDTO);

    List<User> getListOnlineServer();
    List<User> getListOnlineServerByTeamId(Integer teamId);

    List<User> getListOnlineCustomer();

    Boolean updateConversationEnd(Integer conversationId);

    OnlineSessionVO getOnlineSessionVos();

    ConversationStateVO getConversationStateVO(TimeQuery timeQuery);

    List<User> getListOnlineUser();


    List<ConverSationStateBarVO> getConverSationStateBarVos(TimeQuery timeQuery);

    Conversation getConversationById(Integer conversationId);


}
