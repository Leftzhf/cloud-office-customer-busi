package com.cloud.office.customer.busi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.office.customer.busi.service_management.dto.LeaveInfoDto;
import com.cloud.office.customer.busi.service_management.entity.TbLeaveInfo;
import com.cloud.office.customer.busi.service_management.vo.LeaveInfoVo;
import com.cloud.office.customer.busi.vo.PageVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2023-04-22
 */
public interface ITbLeaveInfoService extends IService<TbLeaveInfo> {

    PageVo<LeaveInfoVo> getLeaveInfoPageList(LeaveInfoDto leaveInfoDto);

    Boolean  addLeaveInfo(TbLeaveInfo leaveInfo);

    Boolean updateLeaveInfo(TbLeaveInfo leaveInfo);

    Boolean deleteLeaveInfo(Integer leaveInfoId);
}
