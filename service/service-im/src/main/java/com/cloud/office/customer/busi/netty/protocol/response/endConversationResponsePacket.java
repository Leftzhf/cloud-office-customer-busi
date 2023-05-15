package com.cloud.office.customer.busi.netty.protocol.response;

import com.cloud.office.customer.busi.netty.protocol.Packet;
import com.cloud.office.customer.busi.netty.protocol.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/5/16 01:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class endConversationResponsePacket extends Packet {

    private Integer conversationId;

    private Boolean success;

    /**
     * 获取指令
     *
     * @return
     */
    @Override
    public Short getCommand() {
        return Command.END_CONVERSATION_RESPONSE;
    }
}
