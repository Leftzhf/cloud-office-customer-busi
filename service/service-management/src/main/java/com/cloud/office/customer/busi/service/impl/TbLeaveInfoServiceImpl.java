package com.cloud.office.customer.busi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.office.customer.busi.mapper.TbLeaveInfoMapper;
import com.cloud.office.customer.busi.service.ITbLeaveInfoService;
import com.cloud.office.customer.busi.service_management.dto.LeaveInfoDto;
import com.cloud.office.customer.busi.service_management.entity.TbLeaveInfo;
import com.cloud.office.customer.busi.service_management.vo.LeaveInfoVo;
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
public class TbLeaveInfoServiceImpl extends ServiceImpl<TbLeaveInfoMapper, TbLeaveInfo> implements ITbLeaveInfoService {

    @Autowired
    private TbLeaveInfoMapper tbLeaveInfoMapper;

    @Override
    public PageVo<LeaveInfoVo> getLeaveInfoPageList(LeaveInfoDto leaveInfoDto) {
        LambdaQueryWrapper<TbLeaveInfo> lambdaQuery = new LambdaQueryWrapper<>();
        if (leaveInfoDto.getConversationId()!=null){
           lambdaQuery.eq(TbLeaveInfo::getConversationId,leaveInfoDto.getConversationId());
        }
        LeaveInfoDto leavePages = tbLeaveInfoMapper.selectPage(leaveInfoDto, lambdaQuery);
        List<LeaveInfoVo> collect = leavePages.getRecords().stream().map(item -> {
            LeaveInfoVo leaveInfoVo = new LeaveInfoVo();
            leaveInfoVo.setId(item.getId());
            leaveInfoVo.setContent(item.getContent());
            leaveInfoVo.setEmail(item.getEmail());
            leaveInfoVo.setPhoneNumber(item.getPhoneNumber());
            leaveInfoVo.setCreateAt(item.getCreatedAt());
            return leaveInfoVo;
        }).collect(Collectors.toList());
        return PageUtils.getPageVo(leavePages,collect);
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
