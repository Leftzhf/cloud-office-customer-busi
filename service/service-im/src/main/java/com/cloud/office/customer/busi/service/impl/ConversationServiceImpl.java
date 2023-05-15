package com.cloud.office.customer.busi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.office.customer.busi.ServiceUsercenterClient;
import com.cloud.office.customer.busi.enums.ConversationStatusEnum;
import com.cloud.office.customer.busi.enums.RoleEnum;
import com.cloud.office.customer.busi.exception.ApplicationException;
import com.cloud.office.customer.busi.mapper.ConversationMapper;
import com.cloud.office.customer.busi.netty.protocol.response.endConversationResponsePacket;
import com.cloud.office.customer.busi.netty.utils.ChannelUtil;
import com.cloud.office.customer.busi.netty.utils.SessionManager;
import com.cloud.office.customer.busi.service.ConversationService;
import com.cloud.office.customer.busi.service_im.dto.ConversationDTO;
import com.cloud.office.customer.busi.service_im.entity.Conversation;
import com.cloud.office.customer.busi.service_im.entity.Session;
import com.cloud.office.customer.busi.service_im.query.TimeQuery;
import com.cloud.office.customer.busi.service_im.vo.ConversationStateVO;
import com.cloud.office.customer.busi.service_im.vo.OnlineSessionVO;
import com.cloud.office.customer.busi.service_im.vo.ServerToCustomersVO;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.UserDto;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.Role;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import com.cloud.office.customer.busi.utils.DateToolUtil;
import com.cloud.office.customer.busi.utils.RestTemplateUtil;
import com.cloud.office.customer.busi.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ConversationServiceImpl extends ServiceImpl<ConversationMapper, Conversation> implements ConversationService {

    @Autowired
    private ServiceUsercenterClient usercenterClient;

    @Autowired
    private RestTemplateUtil restTemplate;

    @Autowired
    private ConversationMapper conversationMapper;

    /**
     * 查询会话列表
     *
     * @param userId 用户编号
     * @return
     */
    @Override
    public List<Conversation> selectListByUserId(Integer userId) {
        List<Conversation> conversations = baseMapper.selectListByUserId(userId);
        conversations.forEach(item -> {
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
        conversations.forEach(item -> {
            //todo 改成resttemplate请求
            User fromUser = restTemplate.getUserById(item.getFromUserId());
            item.setFromUser(fromUser);
            User toUser = restTemplate.getUserById(item.getToUserId());
            item.setToUser(toUser);
        });
        return conversations;
    }

    @Override
    public List<User> getListOnlineUser() {
        ArrayList<User> onlineUser = new ArrayList<>();
        ChannelUtil.USER_ID_CHANNEL_MAP.forEach((k, v) -> {
            User byUsername = restTemplate.getUserById(k);
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
            for (Role role : roles) {
                if (role.getLevel() == 3) {
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
        List<User> listOnlineCustomer = listOnlineUser.stream().filter(user -> {
            List<Role> roles = user.getRoles();
            Boolean isCustomer = false;
            for (Role role : roles) {
                if (role.getLevel() == 4) {
                    isCustomer = true;
                }
            }
            return isCustomer;
        }).collect(Collectors.toList());
        return listOnlineCustomer;
    }

    @Override
    public Boolean createConversation(ConversationDTO conversationDTO) {
        LambdaQueryWrapper<Conversation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Conversation::getFromUserId, conversationDTO.getFromUserName());
        queryWrapper.eq(Conversation::getToUserId, conversationDTO.getToUserId());
        Conversation conversation = new Conversation();
        //先判读发送用户是否存在，如果不存在则创建用户
        User user = usercenterClient.findByUsername(conversationDTO.getFromUserName());
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
            //获取用户关联权限信息
            User userInfo = usercenterClient.findByUsername(conversationDTO.getFromUserName());
            conversation.setFromUserId(userInfo.getId());
        } else {
            conversation.setFromUserId(user.getId());
        }
        conversation.setToUserId(conversationDTO.getToUserId());
        conversation.setStatus(ConversationStatusEnum.NORMAL.getValue());
        int insert = baseMapper.insert(conversation);
        return insert > 0;
    }

    @Override
    public List<User> getListOnlineServerByTeamId(Integer teamId) {
        List<User> listOnlineServer = this.getListOnlineServer();
        List<User> onliseServer = listOnlineServer.stream().filter(item -> {
            if (item.getTeamId().equals(teamId)) {
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList());
        return onliseServer;
    }

    @Override
    public Boolean updateConversationEnd(Integer conversationId) {
        Conversation conversation = new Conversation();
        conversation.setId(conversationId);
        conversation.setStatus(ConversationStatusEnum.DELETE.getValue());
        int i = baseMapper.updateById(conversation);
        SessionManager sessionManager = SessionManager.getInstance();
        Session session = sessionManager.getSession(conversationId);
        try {
            if (session != null) {
                Integer visitorId = session.getVisitorId();
                //通知访客会话已结束
                ChannelUtil.getChannel(visitorId).writeAndFlush(new endConversationResponsePacket(conversationId, true));
            }
        } catch (Exception e) {
            new ApplicationException("访客不在线");
        }
        return i > 0;
    }

    @Override
    public OnlineSessionVO getOnlineSessionVos() {
        OnlineSessionVO onlineSessionVO = new OnlineSessionVO();
        ArrayList<ServerToCustomersVO> serverToCustomersVOS = new ArrayList<>();
        SessionManager sessionManager = SessionManager.getInstance();
        Map<Integer, Session> allSession = sessionManager.getAllSession();
        onlineSessionVO.setCountSession(allSession.size());
        //把 allSession 组合成onlineSessionVos
        allSession.forEach((k, v) -> {
            ServerToCustomersVO serverToCustomersVO = new ServerToCustomersVO();
            User visitor = usercenterClient.getById(v.getVisitorId());
            User server = usercenterClient.getById(v.getServerId());
            List<User> customerList = new ArrayList<>();
            customerList.add(visitor);
            serverToCustomersVO.setServer(server);
            serverToCustomersVO.setCustomers(customerList);
            serverToCustomersVOS.add(serverToCustomersVO);
        });
        List<ServerToCustomersVO> mergedServerToCustomersVOS = serverToCustomersVOS.stream()
                .collect(Collectors.groupingBy(ServerToCustomersVO::getServer))
                .entrySet().stream()
                .map(entry -> {
                    User server = entry.getKey();
                    List<User> customers = entry.getValue().stream()
                            .flatMap(serverToCustomersVO -> serverToCustomersVO.getCustomers().stream())
                            .collect(Collectors.toList());
                    return new ServerToCustomersVO(server, customers);
                })
                .collect(Collectors.toList());
        onlineSessionVO.setServerToCustomers(mergedServerToCustomersVOS);
        return onlineSessionVO;
    }

    @Override
    public List<ConversationStateVO> getConversationStateVO(TimeQuery timeQuery) {
        LambdaQueryWrapper<Conversation> conversationLambdaQueryWrapper = new LambdaQueryWrapper<>();
        conversationLambdaQueryWrapper.le(Conversation::getCreatedAt, timeQuery.getEndTime());
        conversationLambdaQueryWrapper.ge(Conversation::getCreatedAt, timeQuery.getStartTime());
        List<Conversation> conversations = conversationMapper.selectList(conversationLambdaQueryWrapper);
        ResultVo response = usercenterClient.getUserByRole(RoleEnum.SERVER.getLevel());
        List<User> userServer = (List<User>) response.getData();

        //把userServer转成按照id为键的map
        Map<Integer, User> userServerMap = userServer.stream().collect(Collectors.toMap(User::getId, user -> user));
        List<ConversationStateVO> conversationStateVOS = new ArrayList<>();
        //把conversations按照to_user_id和created_at分组
        Map<String, Map<Integer, Long>> collect = conversations.stream()
                .collect(Collectors.groupingBy(item -> DateToolUtil.getWeekStringByDate(item.getCreatedAt()),
                        Collectors.groupingBy(item -> item.getToUserId(), Collectors.counting())));
        collect.forEach((k, v) -> {
            ConversationStateVO conversationStateVO = new ConversationStateVO();
            conversationStateVO.setDateString(k);
            v.forEach((k1, v1) -> {
                ConversationStateVO.State state = new ConversationStateVO.State(userServerMap.get(k1).getNickname(), v1.intValue());
                conversationStateVO.setStateByWeek(state);
            });
            conversationStateVOS.add(conversationStateVO);
        });
        //按照dateString升序
        conversationStateVOS.sort(Comparator.comparing(ConversationStateVO::getDateString));
        return conversationStateVOS;
    }

}
