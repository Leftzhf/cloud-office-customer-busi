package com.cloud.office.customer.busi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.office.customer.busi.mapper.TbLeaveInfoMapper;
import com.cloud.office.customer.busi.service.ITbLeaveInfoService;
import com.cloud.office.customer.busi.service_openportal.domain.dto.LeaveInfoDto;
import com.cloud.office.customer.busi.service_openportal.domain.entity.TbLeaveInfo;
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
public class TbLeaveInfoServiceImpl extends ServiceImpl<TbLeaveInfoMapper, TbLeaveInfo> implements ITbLeaveInfoService {

    @Autowired
    private TbLeaveInfoMapper tbLeaveInfoMapper;

    @Override
    public IPage<TbLeaveInfo> getLeaveInfoPageList(LeaveInfoDto leaveInfoDto) {
        LambdaQueryWrapper<TbLeaveInfo> lambdaQuery = new LambdaQueryWrapper<>();
        if (leaveInfoDto.getConversationId()!=null){
           lambdaQuery.eq(TbLeaveInfo::getConversationId,leaveInfoDto.getConversationId());
        }
        return  tbLeaveInfoMapper.selectPage(leaveInfoDto, lambdaQuery);
    }

    @Override
    public Boolean addLeaveInfo(TbLeaveInfo leaveInfo) {
       return tbLeaveInfoMapper.insert(leaveInfo) >0;
    }

    @Override
    public Boolean updateLeaveInfo(TbLeaveInfo leaveInfo) {
        return  tbLeaveInfoMapper.updateById(leaveInfo) > 0;
    }

    @Override
    public Boolean deleteLeaveInfo(Integer leaveInfoId) {

        return tbLeaveInfoMapper.deleteById(leaveInfoId) > 0;
    }
}
