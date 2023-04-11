package com.cloud.office.customer.busi.service;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.TCostomerServerRule;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.TCostomerServerUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.office.customer.busi.service_usercenter.domain.vo.RegisterBody;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author leftleft
 * @since 2023-03-17
 */
public interface ITCostomerServerUserService extends IService<TCostomerServerUser> {

    Integer registerUserInfo( TCostomerServerUser userInfo);
    TCostomerServerUser getUserByName(String username);

    Map<String, List<TCostomerServerRule>> getRuleByUserId(String userId);
}
