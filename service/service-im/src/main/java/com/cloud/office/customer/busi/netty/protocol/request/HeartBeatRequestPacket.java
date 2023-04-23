package com.cloud.office.customer.busi.netty.protocol.request;

import com.cloud.office.customer.busi.netty.protocol.Packet;
import com.cloud.office.customer.busi.netty.protocol.command.Command;
import lombok.Data;

/**
 */
@Data
public class HeartBeatRequestPacket extends Packet {

    @Override
    public Short getCommand() {
        return Command.HEART_BEAT_REQUEST;
    }
}
