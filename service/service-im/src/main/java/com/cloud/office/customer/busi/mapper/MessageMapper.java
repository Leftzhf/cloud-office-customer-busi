package com.cloud.office.customer.busi.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.office.customer.busi.service_im.dto.MessageListDto;
import com.cloud.office.customer.busi.service_im.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {

    /**
     * 查询信息
     *
     * @param id 信息编号
     * @return
     */
    Message selectById(Integer id);

    /**
     * 查询消息分页数据
     *
     * @param messageListDto 消息分页查询条件
     * @return
     */
    List<Message> selectMessageList(MessageListDto messageListDto);
}
