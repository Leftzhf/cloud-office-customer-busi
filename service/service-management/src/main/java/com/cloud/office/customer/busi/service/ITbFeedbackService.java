package com.cloud.office.customer.busi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.office.customer.busi.service_openportal.domain.dto.FeedBackDto;
import com.cloud.office.customer.busi.service_openportal.domain.entity.TbFeedback;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2023-04-22
 */
public interface ITbFeedbackService extends IService<TbFeedback> {

    IPage<TbFeedback> getFeedBackPageList(FeedBackDto feedBackQuery);

    Boolean  addFeedBack(TbFeedback feedBack);

    Boolean updateFeedBack(TbFeedback feedBack);

    Boolean deleteFeedBack(Integer feedBackId);

}
