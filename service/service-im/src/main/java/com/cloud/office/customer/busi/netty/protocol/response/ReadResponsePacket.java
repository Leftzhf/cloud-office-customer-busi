package com.cloud.office.customer.busi.netty.protocol.response;

import com.cloud.office.customer.busi.netty.protocol.Packet;
import com.cloud.office.customer.busi.netty.protocol.command.Command;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/4/20 16:02
 */
@Data
@NoArgsConstructor
public class ReadResponsePacket extends Packet {


    //userId发的消息已经读完了，通知你回调修改状态
    private List<Integer> readedList;


    /**
     * 已读用户编号
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
        return Command.READ_RESPONSE;
    }
}
