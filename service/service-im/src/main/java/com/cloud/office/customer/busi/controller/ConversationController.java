package com.cloud.office.customer.busi.controller;


import com.cloud.office.customer.busi.ServiceUsercenterClient;
import com.cloud.office.customer.busi.service.ConversationService;
import com.cloud.office.customer.busi.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 */
@Slf4j
@RestController
@RequestMapping("/conversation")
public class ConversationController {
    @Autowired
    private ServiceUsercenterClient userService;
    @Autowired
    private ConversationService conversationService;

    @GetMapping("/list/{userId}")
    public ResultVo getConversationList(@PathVariable Integer userId) {
        log.info("获取会话列表数据,userId={}", userId);
//        User user = userService.findByUsername("admin");
        return ResultVo.success(conversationService.selectListByUserId(userId));
    }
}
