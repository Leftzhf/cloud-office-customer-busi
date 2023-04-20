package com.cloud.office.customer.busi.netty.protocol.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.cloud.office.customer.busi.enums.MessageTypeEnum;
import com.cloud.office.customer.busi.netty.protocol.Packet;
import com.cloud.office.customer.busi.netty.protocol.command.Command;
import com.cloud.office.customer.busi.util.EnumValueDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 服务端发送至客户端的消息数据包
 *
 * @author leftleft
 * @date 2023-04-20
 */
@Data
@NoArgsConstructor
public class MessageResponsePacket extends Packet {

    /**
     * 消息编号
     */
    private Integer id;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 发送方用户编号
     */
    private Integer fromUserId;

    /**
     * 接收方用户编号
     */
    private Integer toUserId;

    /**
     * 消息类型 [1.文字 2.图片]
     */
    @JSONField(serializeUsing = EnumValueDeserializer.class, deserializeUsing = EnumValueDeserializer.class)
    private MessageTypeEnum type;

    /**
     * 消息状态 [1.未读 2.已读]
     */
//    @JSONField(serializeUsing = EnumValueDeserializer.class, deserializeUsing = EnumValueDeserializer.class)
    private Integer status;

    /**
     * 创建时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    /**
     * 修改时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @Override
    public Short getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
