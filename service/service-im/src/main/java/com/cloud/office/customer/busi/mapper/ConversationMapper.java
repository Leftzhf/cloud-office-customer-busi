package com.cloud.office.customer.busi.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.office.customer.busi.service_im.entity.Conversation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConversationMapper extends BaseMapper<Conversation> {

    /**
     * 查询会话列表
     *
     * @param userId 用户编号
     * @return
     */
    List<Conversation> selectListByUserId(Integer userId);

}
