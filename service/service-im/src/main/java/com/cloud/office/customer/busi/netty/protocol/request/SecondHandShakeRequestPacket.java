package com.cloud.office.customer.busi.netty.protocol.request;

import com.cloud.office.customer.busi.netty.protocol.Packet;
import com.cloud.office.customer.busi.netty.protocol.command.Command;
import lombok.Data;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/5/11 13:44
 */
@Data
public class SecondHandShakeRequestPacket extends Packet {

    private Integer sessionId;

    private Integer visitorId;

    private Integer serverId;

    /**
     * 获取指令
     *
     * @return
     */
    @Override
    public Short getCommand() {
        return Command.SECOND_HAND_SHAKE_REQUEST;
    }
}
