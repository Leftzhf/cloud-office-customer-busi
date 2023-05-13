package com.cloud.office.customer.busi.netty.protocol.response;

import com.cloud.office.customer.busi.netty.protocol.Packet;
import com.cloud.office.customer.busi.netty.protocol.command.Command;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/5/11 13:49
 */
@Data
@NoArgsConstructor
public class SecondHandShakeResponsePacket extends Packet {

    private String visitorKey;

    private String serverKey;

    /**
     * 获取指令
     *
     * @return
     */
    @Override
    public Short getCommand() {
        return Command.SECOND_HAND_SHAKE_RESPONSE;
    }
}
