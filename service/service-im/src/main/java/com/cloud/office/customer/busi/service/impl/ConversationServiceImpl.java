package com.cloud.office.customer.busi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.office.customer.busi.ServiceUsercenterClient;
import com.cloud.office.customer.busi.mapper.ConversationMapper;
import com.cloud.office.customer.busi.service.ConversationService;
import com.cloud.office.customer.busi.service_im.entity.Conversation;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import com.cloud.office.customer.busi.utils.RestTemplateUtil;
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
public class ConversationServiceImpl extends ServiceImpl<ConversationMapper, Conversation> implements ConversationService {

    @Autowired
    private ServiceUsercenterClient usercenterClient;

    @Autowired
    private RestTemplateUtil restTemplate;
    /**
     * 查询会话列表
     *
     * @param userId 用户编号
     * @return
     */
    @Override
    public List<Conversation> selectListByUserId(Integer userId) {
        List<Conversation> conversations = baseMapper.selectListByUserId(userId);
        conversations.forEach(item->{
            User fromUser = usercenterClient.getUserById(item.getFromUserId());
            item.setFromUser(fromUser);
            User toUser = usercenterClient.getUserById(item.getToUserId());
            item.setToUser(toUser);
        });
        return conversations;
    }
    @Override
    public List<Conversation> selectListByUserIdRes(Integer userId) {
        List<Conversation> conversations = baseMapper.selectListByUserId(userId);
        conversations.forEach(item->{
            //todo 改成resttemplate请求
            User fromUser = restTemplate.getUserById(item.getFromUserId());
            item.setFromUser(fromUser);
            User toUser = restTemplate.getUserById(item.getToUserId());
            item.setToUser(toUser);
        });
        return conversations;
    }
}
