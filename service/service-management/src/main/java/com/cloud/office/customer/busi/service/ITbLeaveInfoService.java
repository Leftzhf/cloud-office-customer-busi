package com.cloud.office.customer.busi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.office.customer.busi.service_openportal.domain.dto.LeaveInfoDto;
import com.cloud.office.customer.busi.service_openportal.domain.entity.TbLeaveInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2023-04-22
 */
public interface ITbLeaveInfoService extends IService<TbLeaveInfo> {

    IPage<TbLeaveInfo> getLeaveInfoPageList(LeaveInfoDto leaveInfoDto);

    Boolean  addLeaveInfo(TbLeaveInfo leaveInfo);

    Boolean updateLeaveInfo(TbLeaveInfo leaveInfo);

    Boolean deleteLeaveInfo(Integer leaveInfoId);
}
