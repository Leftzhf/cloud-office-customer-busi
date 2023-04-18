package com.cloud.office.customer.busi.netty.protocol.request;

import com.cloud.office.customer.busi.netty.protocol.Packet;
import com.cloud.office.customer.busi.netty.protocol.command.Command;
import lombok.Data;

/**
 * 登录请求数据包
 *
 * @author feng
 * @date 2019-04-20
 */
@Data
public class LoginRequestPacket extends Packet {

    /**
     * 用户名
     * 客服使用username 访客使用客户端生成的唯一标识
     */
    private String username;

    /**
     * 团队编号
     */
    private Integer teamId;

    @Override
    public Short getCommand() {
        return Command.LOGIN_REQUEST;
    }

}
