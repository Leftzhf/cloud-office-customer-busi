package com.cloud.office.customer.busi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.office.customer.busi.service_im.entity.Conversation;

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
}
