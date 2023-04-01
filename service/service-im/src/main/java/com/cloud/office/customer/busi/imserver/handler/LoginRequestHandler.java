//package com.cloud.office.customer.busi.imserver.handler;
//
//import com.cloud.office.customer.busi.imserver.protocol.MessageProto;
//import com.cloud.office.customer.busi.imserver.session.Session;
//import com.cloud.office.customer.busi.utils.SessionUtil;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.SimpleChannelInboundHandler;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
//import java.util.Date;
//import java.util.UUID;
//
//public class LoginRequestHandler extends SimpleChannelInboundHandler<MessageProto.ImMessage> {
//    //日志记录器
//    private static final Logger log = LoggerFactory.getLogger(LoginRequestHandler.class);
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, MessageProto.ImMessage loginRequestProto) {
//        if (){
//            //访客登录，直接分配一个userId
//            String userId = randomUserId();
//            loginResponsePacket.setUserId(userId);
//            loginResponsePacket.setUserName(userId);
//           log.info("访客{}登录成功",userId);
//           //加入session
//            SessionUtil.bindSession(new Session(userId, loginRequestProto.getUserName()), ctx.channel());
//        }
//        if (valid(loginRequestProto)) {
//            loginResponsePacket.setUserName(loginRequestProto.getUserName());
//            loginResponsePacket.setSuccess(true);
//            //TODO userId改成数据库中的id,访客端不需要登录，直接颁发一个userID，客服需要登录，从数据库里拿
//            String userId = randomUserId();
//
//            loginResponsePacket.setUserId(userId);
//            System.out.println("[" + loginRequestProto.getUserName() + "]登录成功");
//            SessionUtil.bindSession(new Session(userId, loginRequestProto.getUserName()), ctx.channel());
//        } else {
//            loginResponsePacket.setReason("账号密码校验失败");
//            loginResponsePacket.setSuccess(false);
//            System.out.println(new Date() + ": 登录失败!");
//        }
//
//        // 登录响应
//        ctx.channel().writeAndFlush(loginResponsePacket);
//    }
//
//    private boolean valid(LoginRequestFrame loginRequestPacket) {
//        //TODO 登录校验采用token jwt
//        return true;
//    }
//
//    private static String randomUserId() {
//        return UUID.randomUUID().toString().split("-")[0];
//    }
//
//    @Override
//    public void channelInactive(ChannelHandlerContext ctx) {
//        SessionUtil.unBindSession(ctx.channel());
//    }
//}
