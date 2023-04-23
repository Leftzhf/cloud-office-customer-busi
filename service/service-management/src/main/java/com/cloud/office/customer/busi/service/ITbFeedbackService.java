package com.cloud.office.customer.busi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.office.customer.busi.service_management.dto.FeedBackDto;
import com.cloud.office.customer.busi.service_management.entity.TbFeedback;
import com.cloud.office.customer.busi.service_management.vo.FeedBackVo;
import com.cloud.office.customer.busi.vo.PageVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2023-04-22
 */
public interface ITbFeedbackService extends IService<TbFeedback> {

    PageVo<FeedBackVo> getFeedBackPageList(FeedBackDto feedBackQuery);

    Boolean  addFeedBack(TbFeedback feedBack);

    Boolean updateFeedBack(TbFeedback feedBack);

    Boolean deleteFeedBack(Integer feedBackId);

}
