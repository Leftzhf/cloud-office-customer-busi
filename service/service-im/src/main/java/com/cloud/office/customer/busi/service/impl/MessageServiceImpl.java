package com.cloud.office.customer.busi.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.office.customer.busi.ServiceUsercenterClient;
import com.cloud.office.customer.busi.enums.MessageStatusEnum;
import com.cloud.office.customer.busi.mapper.MessageMapper;
import com.cloud.office.customer.busi.netty.protocol.response.ReadResponsePacket;
import com.cloud.office.customer.busi.netty.utils.ChannelUtil;
import com.cloud.office.customer.busi.service.MessageService;
import com.cloud.office.customer.busi.service_im.dto.MessageListDto;
import com.cloud.office.customer.busi.service_im.entity.Message;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Autowired
    private ServiceUsercenterClient serviceUsercenterClient;
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
        //todo 通知对方客户端更新消息状态
       return makeReaded(messages,messageListDto.getContactUserId(),messageListDto.getUserId());
    }

    @Override
    public Integer deleteMessage(Integer messageId) {
        int i = baseMapper.deleteById(messageId);
        return i;
    }
    private List<Message> makeReaded(List<Message> messageList,Integer toUserId,Integer userId){
        ArrayList<Integer> readedList = new ArrayList<>();
        if (messageList!=null&&messageList.size() > 0){
            messageList.stream().forEach(item->{
                if((item.getFromUserId().equals(toUserId))&&item.getStatus().equals(MessageStatusEnum.UNREAD)) {
                    item.setStatus(MessageStatusEnum.READ);
                    readedList.add(item.getId());
                }
            });
            //批量更新
            saveOrUpdateBatch(messageList);
            //通知对方已读
            User currentUser = serviceUsercenterClient.getById(toUserId);
            Channel toChannel = ChannelUtil.getChannel(currentUser.getUsername());
            if (toChannel != null && ChannelUtil.hasLogin(toChannel)) {
                //todo 如果对方在线，通知它消息已读,更新消息状态
                // 最后一条消息id之前的，他发的，数据，都设置为已读
                //注意这里的userId是指的对方，contract指的是我方，方便前端
                ReadResponsePacket readResponsePacket = new ReadResponsePacket();
                readResponsePacket.setReadedList(readedList);
                readResponsePacket.setUserId(toUserId);
                readResponsePacket.setContactUserId(userId);
                toChannel.writeAndFlush(readResponsePacket);
            }
        }

        return messageList;
    }
}
