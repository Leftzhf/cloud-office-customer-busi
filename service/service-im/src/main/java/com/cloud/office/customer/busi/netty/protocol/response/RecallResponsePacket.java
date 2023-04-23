package com.cloud.office.customer.busi.netty.protocol.response;

import com.cloud.office.customer.busi.netty.protocol.Packet;
import com.cloud.office.customer.busi.netty.protocol.command.Command;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/23 23:22
 */
@Data
@NoArgsConstructor
public class RecallResponsePacket  extends Packet {


    //撤回的消息Id
    private Integer messageId;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 联系人用户编号
     */
    private Integer contactUserId;


    /**
     * 获取指令
     *
     * @return
     */
    @Override
    public Short getCommand() {
        return Command.RECALL_RESPONSE;
    }
}
