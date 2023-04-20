package com.cloud.office.customer.busi.controller;

import com.alibaba.fastjson.JSON;
import com.cloud.office.customer.busi.service.MessageService;
import com.cloud.office.customer.busi.service_im.dto.MessageListDto;
import com.cloud.office.customer.busi.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 消息
 *
 * @author leftleft
 */
@Slf4j

@Api(tags = "消息接口",description = "消息接口")
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/list")
    @ApiOperation(value = "获取消息历史")
    public ResultVo getMessagePageList(@RequestBody MessageListDto messageListDto) {
        log.info("获取消息数据,{}", JSON.toJSONString(messageListDto));
        return ResultVo.success(messageService.findMessageList(messageListDto));
    }
    //todo 获取秘钥接口

    @DeleteMapping("/delete/{messageId}")
    @ApiOperation(value = "撤回(删除)消息")
    public ResultVo deleteMessage(@PathVariable Integer messageId) {
        log.info("删除消息数据,{}",messageId);
        return ResultVo.success(messageService.deleteMessage(messageId));
    }
}
