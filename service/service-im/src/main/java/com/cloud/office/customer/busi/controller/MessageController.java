package com.cloud.office.customer.busi.controller;

import com.alibaba.fastjson.JSON;
import com.cloud.office.customer.busi.vo.ResultVo;
import com.cloud.office.customer.busi.service.MessageService;
import com.cloud.office.customer.busi.service_im.dto.MessageListDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息
 *
 * @author leftleft
 */
@Slf4j
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/list")
    public ResultVo getMessagePageList(@RequestBody MessageListDto messageListDto) {
        log.info("获取消息数据,{}", JSON.toJSONString(messageListDto));
        return ResultVo.success(messageService.findMessageList(messageListDto));
    }
    //todo 获取秘钥接口
}
