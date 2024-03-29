package com.cloud.office.customer.busi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.office.customer.busi.service_usercenter.domain.vo.TeamInfoVo;
import com.cloud.office.customer.busi.vo.PageVo;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.TeamPageDto;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.Team;

import java.util.List;

/**
 */
public interface TeamService extends IService<Team> {

    /**
     * 删除团队
     * 注意只能删除没有成员的团队
     *
     * @param teamId 团队编号
     */
    void deleteTeam(Integer teamId);

    /**
     * 获取带分页的团队列表
     *
     * @param teamPageDto 分页查询条件
     * @return
     */
    PageVo<Team> getTeamPageList(TeamPageDto teamPageDto);

    List<Team> getTeamList();

    TeamInfoVo getTeamInfo(Integer teamId);
}
