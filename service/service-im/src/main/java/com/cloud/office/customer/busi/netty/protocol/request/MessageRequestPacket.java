package com.cloud.office.customer.busi.netty.protocol.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.cloud.office.customer.busi.common.enums.MessageTypeEnum;
import com.cloud.office.customer.busi.common.util.EnumValueDeserializer;
import com.cloud.office.customer.busi.netty.protocol.Packet;
import com.cloud.office.customer.busi.netty.protocol.command.Command;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 客户端发送至服务端的消息数据包
 *
 * @author feng
 * @date 2019-04-20
 */
@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息类型 [1.文字 2.图片]
     */
    @JSONField(serializeUsing = EnumValueDeserializer.class, deserializeUsing = EnumValueDeserializer.class)
    private MessageTypeEnum type;

    /**
     * 接收方用户编号
     */
    private Integer toUserId;

    @Override
    public Short getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}