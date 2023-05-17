package com.cloud.office.customer.busi.controller;


import com.cloud.office.customer.busi.ServiceUsercenterClient;
import com.cloud.office.customer.busi.service.ConversationService;
import com.cloud.office.customer.busi.service_im.dto.ConversationDTO;
import com.cloud.office.customer.busi.service_im.query.TimeQuery;
import com.cloud.office.customer.busi.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 */
@Slf4j
@RestController
@RequestMapping("/conversation")
@Api(tags = "会话接口",description = "会话接口")
public class ConversationController {
    @Autowired
    private ServiceUsercenterClient userService;
    @Autowired
    private ConversationService conversationService;

    @PostMapping("/distribution")
    @ApiOperation(value = "分配客服")
    public ResultVo getConversationDistributionVO(@RequestBody ConversationDTO conversationDTO){
        log.info("分配客服,username={}", conversationDTO.getFromUserName());
        return ResultVo.success(conversationService.getConversationDistributionVO(conversationDTO));
    }

    @PutMapping("/create")
    @ApiOperation(value = "创建会话")
    public ResultVo createConversation(@RequestBody ConversationDTO conversationDTO) {
        log.info("创建会话,userId={}", conversationDTO);
        return ResultVo.success(conversationService.createConversation(conversationDTO));
    }
    @GetMapping("/list/{userId}")
    @ApiOperation(value = "获取会话列表")
    public ResultVo getConversationList(@PathVariable Integer userId) {
        log.info("获取会话列表数据,userId={}", userId);
        return ResultVo.success(conversationService.selectListByUserId(userId));
    }
    @GetMapping("/list/online/server")
    @ApiOperation(value = "获取在线客服")
    public ResultVo getListOnlineServer() {
        return ResultVo.success(conversationService.getListOnlineServer());
    }
    @GetMapping("/list/online/server/{teamId}")
    @ApiOperation(value = "根据团队id获取在线客服")
    public ResultVo getListOnlineServerByTeamId(@PathVariable Integer teamId) {
        return ResultVo.success(conversationService.getListOnlineServerByTeamId(teamId));
    }
    @GetMapping("/list/online/customer")
    @ApiOperation(value = "获取在线访客")
    public ResultVo getListOnlineCustomer() {
        return ResultVo.success(conversationService.getListOnlineCustomer());
    }

    @PostMapping("/update/end/{conversationId}")
    @ApiOperation(value = "更新会话状态为结束")
    public ResultVo updateConversationEnd(@PathVariable Integer conversationId) {
        return ResultVo.success(conversationService.updateConversationEnd(conversationId));
    }

    //获取在线会话信息
    @GetMapping("/online")
    @ApiOperation(value = "获取在线会话")
    public ResultVo getOnlineConversation() {
        return ResultVo.success(conversationService.getOnlineSessionVos());
    }

    //获取客服咨询情况历史
    @PostMapping("/state")
    @ApiOperation(value = "获取客服会话折线图统计")
    public ResultVo getConversationState(@RequestBody TimeQuery timeQuery) {
        return ResultVo.success(conversationService.getConversationStateVO(timeQuery));
    }

    //获取客服咨询情况历史
    @PostMapping("/barState")
    @ApiOperation(value = "获取客服会话饼图图统计")
    public ResultVo getConversationBarState(@RequestBody TimeQuery timeQuery) {
        return ResultVo.success(conversationService.getConverSationStateBarVos(timeQuery));
    }

}
