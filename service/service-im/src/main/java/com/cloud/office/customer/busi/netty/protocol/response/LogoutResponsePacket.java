package com.cloud.office.customer.busi.netty.protocol.response;

import com.cloud.office.customer.busi.netty.protocol.Packet;
import com.cloud.office.customer.busi.netty.protocol.command.Command;
import lombok.Data;

/**
 * @author leftleft
 * @date 2023-04-21
 */
@Data
public class LogoutResponsePacket extends Packet {

    private Boolean success;

    @Override
    public Short getCommand() {
        return Command.LOGOUT_RESPONSE;
    }
}
