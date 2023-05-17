package com.cloud.office.customer.busi.netty.protocol.response;

import com.cloud.office.customer.busi.netty.protocol.Packet;
import com.cloud.office.customer.busi.netty.protocol.command.Command;
import com.cloud.office.customer.busi.service_im.entity.Conversation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/5/16 19:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateConversationResponsePacket extends Packet {

    Conversation conversation;

    /**
     * 获取指令
     *
     * @return
     */
    @Override
    public Short getCommand() {
        return Command.CREATE_CONVERSATION_RESPONSE;
    }
}
