package com.cloud.office.customer.busi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.office.customer.busi.mapper.TbFeedbackMapper;
import com.cloud.office.customer.busi.service.ITbFeedbackService;
import com.cloud.office.customer.busi.service_openportal.domain.dto.FeedBackDto;
import com.cloud.office.customer.busi.service_openportal.domain.entity.TbFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private TbFeedbackMapper feedbackMapper;
    @Override
    public IPage<TbFeedback> getFeedBackPageList(FeedBackDto feedBackQuery) {
        LambdaQueryWrapper<TbFeedback> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TbFeedback::getConversationId,feedBackQuery.getConversationId() );

       return feedbackMapper.selectPage(feedBackQuery,lambdaQueryWrapper);
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
