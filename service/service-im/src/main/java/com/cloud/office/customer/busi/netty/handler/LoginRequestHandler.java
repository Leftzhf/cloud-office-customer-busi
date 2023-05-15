package com.cloud.office.customer.busi.netty.handler;

import com.alibaba.fastjson.JSON;
import com.cloud.office.customer.busi.ServiceUsercenterClient;
import com.cloud.office.customer.busi.base.TimeEntity;
import com.cloud.office.customer.busi.netty.protocol.request.LoginRequestPacket;
import com.cloud.office.customer.busi.netty.protocol.response.LoginResponsePacket;
import com.cloud.office.customer.busi.netty.utils.AesEncryptUtil;
import com.cloud.office.customer.busi.netty.utils.ChannelUtil;
import com.cloud.office.customer.busi.service.ConversationService;
import com.cloud.office.customer.busi.service_im.entity.Conversation;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.UserDto;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.Role;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import com.cloud.office.customer.busi.utils.RestTemplateUtil;
import com.cloud.office.customer.busi.utils.ServerDistributionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 第一次握手请求逻辑处理器,使用feign调用失败，所以这里使用RestTemplate进行远程调用
 *主要绑定用户在线状态信息和颁发密钥
 *
 *
 */
@Slf4j
@ChannelHandler.Sharable
@Component
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

//    @Autowired
//    RestTemplate restTemplate ;
    @Autowired
    RestTemplateUtil restTemplateRemote;
    @Autowired
    private ServiceUsercenterClient userService;

    @Autowired
    private ConversationService conversationService;

//    @Autowired
//    private TeamService teamService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        // 处理登录请求握手响应数据包
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();

        log.info("收到第一次握手: 用户名{}",msg.getUsername());
        // 用户名不能为空
        if (StringUtils.isEmpty(msg.getUsername())) {
            log.info("登录失败,username不能为空");
            loginResponsePacket.setSuccess(false);
            ctx.channel().writeAndFlush(loginResponsePacket);
            return;
        }
        User user = restTemplateRemote.findByUsername(msg.getUsername());
        // 如果没有用户，则新建
        Integer userId = null;
        if (user == null) {
            user = new User();
            user.setNickname("访客");
            user.setUsername(msg.getUsername());
            user.setTeamId(msg.getTeamId());
            user.setPassword("123456");
            user.setAvatar("https://leftelft-picgo-1312794111.cos.ap-guangzhou.myqcloud.com/img/u=699796558,3208220781&fm=253&fmt=auto&app=138&f=JPEG.webp");
            // TODO:访客权限暂时写死，后面需要抽到配置文件里
            List<String> roleNameEns = new ArrayList<>();
            roleNameEns.add("ROLE_VISITOR");

            UserDto userDto = new UserDto();
            userDto.setUserInfo(user);
            userDto.setRoleNameEns(roleNameEns);
            // 新增用户
            restTemplateRemote.addUser(userDto);
        }else {
            userId = user.getId();
        }

        // 判断是否是访客
        boolean isVisitor = false;
        if (user.getRoles() != null && user.getRoles().size() > 0) {
            for (Role role : user.getRoles()) {
                if ("ROLE_VISITOR".equals(role.getNameEn())) {
                    isVisitor = true;
                    break;
                }
            }
        }

        //查询会话表，如果有访客的会话则直接使用，没有则新建一个会话
        if (isVisitor) {
            List<Conversation> conversations = conversationService.selectListByUserIdRes(user.getId());
            //如果有会话则直接从会话获取联系人
            if (conversations != null && conversations.size() > 0) {
                // 时间排序,取最新的会话
                conversations.sort(Comparator.comparing(TimeEntity::getUpdatedAt));
                Conversation conversation = conversations.get(0);
                Integer contactUserId = 0;
                if (!conversation.getFromUserId().equals(user.getId())) {
                    contactUserId = conversation.getFromUserId();
                }
                if (!conversation.getToUserId().equals(user.getId())) {
                    contactUserId = conversation.getToUserId();
                }
                // 获取会话联系人
                User contact = restTemplateRemote.getById(contactUserId);
                loginResponsePacket.setConversationId(conversation.getId());
                loginResponsePacket.setContact(contact);
            } else {
                //如果没有会话，则需要转接客服，从团队里面转接
                if (user.getTeamId() == null || user.getTeamId().equals(0)) {
                    log.info("登录失败,teamId不能为空");
                    loginResponsePacket.setSuccess(false);
                    ctx.channel().writeAndFlush(loginResponsePacket);
                    return;
                }
                // 获取在线客服列表,过滤掉访客用户,
                List<User> listOnlineServer = conversationService.getListOnlineServer();
                //根据teamId获取对应产品线客服列表
                List<User> teamServer = listOnlineServer.stream().filter(item -> {
                    return item.getTeamId().equals(msg.getTeamId());
                }).collect(Collectors.toList());
                if (CollectionUtils.isEmpty(teamServer)){
                    log.info("登录失败,teamId={}的团队没有在线客服",user.getTeamId());
                    loginResponsePacket.setSuccess(false);
                    ctx.channel().writeAndFlush(loginResponsePacket);
                    return;
                }
                // 通过动态客服分配器来分配在线的客服
                User contact = ServerDistributionUtil.getServerByPolling(teamServer);

                // 创建会话
                Conversation conversation = new Conversation();
                conversation.setFromUserId(user.getId());
                conversation.setToUserId(contact.getId());
                conversation.setStatus(1);
                conversationService.save(conversation);
                //设置会话id
                loginResponsePacket.setConversationId(conversation.getId());
                //设置响应消息
                loginResponsePacket.setContact(contact);
            }
        }

        log.info("登录成功,user={}", JSON.toJSONString(user));
        //生成密钥
        String secretKey = AesEncryptUtil.generateKeyAndIv();
        // 保存用户信息和channel对应关系
        ChannelUtil.bindUser(user, ctx.channel(),loginResponsePacket.getConversationId(),secretKey);
        loginResponsePacket.setSecretKey(secretKey);
        loginResponsePacket.setUser(user);
        loginResponsePacket.setSuccess(true);
        //返回握手响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }
}
