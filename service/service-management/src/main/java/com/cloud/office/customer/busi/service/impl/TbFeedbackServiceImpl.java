package com.cloud.office.customer.busi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.office.customer.busi.ServiceUsercenterClient;
import com.cloud.office.customer.busi.mapper.TbFeedbackMapper;
import com.cloud.office.customer.busi.service.ITbFeedbackService;
import com.cloud.office.customer.busi.service_management.dto.FeedBackDto;
import com.cloud.office.customer.busi.service_management.entity.TbFeedback;
import com.cloud.office.customer.busi.service_management.vo.FeedBackVo;
import com.cloud.office.customer.busi.util.PageUtils;
import com.cloud.office.customer.busi.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2023-04-22
 */
@Service
public class TbFeedbackServiceImpl extends ServiceImpl<TbFeedbackMapper, TbFeedback> implements ITbFeedbackService {

    @Autowired
    private ServiceUsercenterClient usercenterClient;
    @Autowired
    private TbFeedbackMapper feedbackMapper;
    @Override
    public PageVo<FeedBackVo> getFeedBackPageList(FeedBackDto feedBackQuery) {
        LambdaQueryWrapper<TbFeedback> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (feedBackQuery.getConversationId()!=null){
            lambdaQueryWrapper.eq(TbFeedback::getConversationId,feedBackQuery.getConversationId() );
        }
        FeedBackDto feedBackDto = feedbackMapper.selectPage(feedBackQuery, lambdaQueryWrapper);
        List<FeedBackVo> records = feedBackDto.getRecords().stream().map(item -> {
            FeedBackVo feedBackVo = new FeedBackVo();
            feedBackVo.setId(item.getId());
            feedBackVo.setContent(item.getContent());
            feedBackVo.setScore(item.getScore());
            feedBackVo.setCreateAt(item.getCreatedAt());
            feedBackVo.setVisitor(usercenterClient.getUserById(item.getVisitorId()));
            feedBackVo.setServer(usercenterClient.getUserById(item.getServerId()));
            return feedBackVo;
        }).collect(Collectors.toList());
        return PageUtils.getPageVo(feedBackDto,records);
    }

    @Override
    public Boolean addFeedBack(TbFeedback feedBack) {
        return feedbackMapper.insert(feedBack) > 0;
    }

    @Override
    public Boolean updateFeedBack(TbFeedback feedBack) {
            return feedbackMapper.updateById(feedBack)>0;
    }

    @Override
    public Boolean deleteFeedBack(Integer feedBackId) {
        return feedbackMapper.deleteById(feedBackId)>0;
    }
}
