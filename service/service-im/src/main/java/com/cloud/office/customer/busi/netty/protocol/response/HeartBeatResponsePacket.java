package com.cloud.office.customer.busi.netty.protocol.response;

import com.cloud.office.customer.busi.netty.protocol.Packet;
import com.cloud.office.customer.busi.netty.protocol.command.Command;
import lombok.Data;

/**
 * @author feng
 * @date 2019-04-21
 */
@Data
public class HeartBeatResponsePacket extends Packet {

    @Override
    public Short getCommand() {
        return Command.HEART_BEAT_RESPONSE;
    }
}