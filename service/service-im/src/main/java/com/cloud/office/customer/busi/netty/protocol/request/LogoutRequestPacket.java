package com.cloud.office.customer.busi.netty.protocol.request;

import com.cloud.office.customer.busi.netty.protocol.Packet;
import com.cloud.office.customer.busi.netty.protocol.command.Command;
import lombok.Data;

/**
 * @author feng
 * @date 2019-04-21
 */
@Data
public class LogoutRequestPacket extends Packet {

    @Override
    public Short getCommand() {
        return Command.LOGOUT_REQUEST;
    }
}
