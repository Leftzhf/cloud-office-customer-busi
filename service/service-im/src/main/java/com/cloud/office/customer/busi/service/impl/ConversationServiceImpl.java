package com.cloud.office.customer.busi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.office.customer.busi.ServiceUsercenterClient;
import com.cloud.office.customer.busi.mapper.ConversationMapper;
import com.cloud.office.customer.busi.netty.utils.ChannelUtil;
import com.cloud.office.customer.busi.service.ConversationService;
import com.cloud.office.customer.busi.service_im.dto.ConversationDTO;
import com.cloud.office.customer.busi.service_im.entity.Conversation;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.UserDto;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.Role;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import com.cloud.office.customer.busi.service_usercenter.domain.vo.UserVo;
import com.cloud.office.customer.busi.utils.RestTemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private List<User> getListOnlineUser() {
        ArrayList<User> onlineUser = new ArrayList<>();
        ChannelUtil.USER_ID_CHANNEL_MAP.forEach((k,v)->{
            User byUsername = restTemplate.findByUsername(k);
            onlineUser.add(byUsername);
        });
        return onlineUser;
    }

    @Override
    public List<User> getListOnlineServer() {
        List<User> listOnlineUser = getListOnlineUser();
        List<User> listOnlineServer = listOnlineUser.stream().filter(user -> {
            List<Role> roles = user.getRoles();
            Boolean isServer = false;
            for (Role role: roles) {
                if (role.getLevel()==3){
                    isServer = true;
                }
            }
            return isServer;
        }).collect(Collectors.toList());
        return listOnlineServer;
    }

    @Override
    public List<User> getListOnlineCustomer() {
        List<User> listOnlineUser = getListOnlineUser();
        List<User> listOnlineServer = listOnlineUser.stream().filter(user -> {
            List<Role> roles = user.getRoles();
            Boolean isServer = false;
            for (Role role: roles) {
                if (role.getLevel()==4){
                    isServer = true;
                }
            }
            return isServer;
        }).collect(Collectors.toList());
        return listOnlineServer;
    }

    @Override
    public Boolean createConversation(ConversationDTO conversationDTO) {
        Conversation conversation = new Conversation();
        //先判读发送用户是否存在，如果不存在则创建用户
        User user = usercenterClient.getById(conversationDTO.getFromUserId());
        if (user == null) {
            user = new User();
            user.setUsername(conversationDTO.getFromUserName());
            user.setTeamId(conversationDTO.getTeamID());
            user.setPassword("123456");
            List<String> roleNameEns = new ArrayList<>();
            roleNameEns.add("ROLE_VISITOR");

            UserDto userDto = new UserDto();
            userDto.setUserInfo(user);
            userDto.setRoleNameEns(roleNameEns);
            // 新增用户
            usercenterClient.addUser(userDto);

            UserVo userInfoByUsername = usercenterClient.findUserInfoByUsername(conversationDTO.getFromUserName());
            conversation.setFromUserId(userInfoByUsername.getUserInfo().getId());
        }else {
            conversation.setFromUserId(conversationDTO.getFromUserId());
        }
        conversation.setToUserId(conversationDTO.getToUserId());
        int insert = baseMapper.insert(conversation);
        if (insert>0){
            return true;
        }else {
            return false;
        }
    }
}
