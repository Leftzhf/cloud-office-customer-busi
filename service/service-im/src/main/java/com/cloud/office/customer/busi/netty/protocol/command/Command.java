package com.cloud.office.customer.busi.netty.protocol.command;

/**
 * 指令
 *
 */
public interface Command {

    Short HEART_BEAT_REQUEST = 1000;
    Short HEART_BEAT_RESPONSE = 1001;

    Short LOGIN_REQUEST = 2000;
    Short LOGIN_RESPONSE = 2001;

    Short LOGOUT_REQUEST = 2002;
    Short LOGOUT_RESPONSE = 2003;

    Short MESSAGE_REQUEST = 3000;
    Short MESSAGE_RESPONSE = 3001;

    Short READ_RESPONSE = 4000;

    Short RECALL_RESPONSE = 5000;

    Short SECOND_HAND_SHAKE_REQUEST = 6000;

    Short SECOND_HAND_SHAKE_RESPONSE = 6001;

    Short END_CONVERSATION_RESPONSE = 7000;

}
