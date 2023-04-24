package com.cloud.office.customer.busi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.office.customer.busi.mapper.TeamMapper;
import com.cloud.office.customer.busi.service.TeamService;
import com.cloud.office.customer.busi.service.UserService;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.TeamPageDto;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.UserPageDto;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.Team;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import com.cloud.office.customer.busi.util.PageUtils;
import com.cloud.office.customer.busi.vo.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team> implements TeamService {

    @Autowired
    @Lazy
    private UserService userService;

    /**
     * 删除团队
     * 注意只能删除没有成员的团队
     *
     * @param teamId 团队编号
     */
    @Override
    public void deleteTeam(Integer teamId) {
        if (getById(teamId) == null) {
            throw new RuntimeException("团队信息不存在，teamId=" + teamId);
        }

        // 检查团队中是否存在成员
        UserPageDto userPageDto = new UserPageDto();
        userPageDto.setTeamId(teamId);
        PageVo<User> pageVo = userService.findUserPageList(userPageDto);
        if (pageVo.getTotalCount() > 0) {
            throw new RuntimeException("删除团队失败，需要先清空团队中的所有成员");
        }

        removeById(teamId);
    }

    /**
     * 获取带分页的团队列表
     *
     * @param teamPageDto 分页查询条件
     * @return
     */
    @Override
    public PageVo<Team> getTeamPageList(TeamPageDto teamPageDto) {
        IPage<Team> page = baseMapper.selectPageList(teamPageDto);
        return PageUtils.getPageVo(page);
    }

    @Override
    public List<Team> getTeamList() {
        List<Team> teams = baseMapper.selectList(new LambdaQueryWrapper<>());
        return teams;
    }
}
