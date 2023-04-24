package com.cloud.office.customer.busi.controller;

import com.cloud.office.customer.busi.vo.ResultVo;
import com.cloud.office.customer.busi.service.TeamService;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.TeamPageDto;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.Team;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/team")
@RestController
@Tag(name = "TeamController", description = "团队接口")
public class TeamController {

    @Autowired
    private TeamService teamService;

    /**
     * 新增团队
     *
     * @param team 团队信息
     * @return
     */
    @PostMapping
    public ResultVo addTeam(@RequestBody Team team) {
        teamService.save(team);
        return ResultVo.success();
    }

    /**
     * 删除团队
     *
     * @param teamId 团队编号
     * @return
     */
    @DeleteMapping("/{teamId}")
    public ResultVo deleteTeam(@PathVariable Integer teamId) {
        teamService.deleteTeam(teamId);
        return ResultVo.success();
    }

    /**
     * 更新团队信息
     *
     * @param team 团队信息
     * @return
     */
    @PutMapping
    public ResultVo updateTeam(@RequestBody Team team) {
        teamService.updateById(team);
        return ResultVo.success();
    }

    /**
     * 获取团队信息
     *
     * @param teamId 团队编号
     * @return
     */
    @GetMapping("/{teamId}")
    public ResultVo getTeam(@PathVariable Integer teamId) {
        return ResultVo.success(teamService.getById(teamId));
    }

    /**
     * 分页查询团队列表
     *
     * @param teamPageDto 分页查询条件
     * @return
     */
    @PostMapping("/list")
    public ResultVo getTeamList(@RequestBody TeamPageDto teamPageDto) {
        return ResultVo.success(teamService.getTeamPageList(teamPageDto));
    }
    @PostMapping("/listinfo")
    public ResultVo getTeamList() {
        return ResultVo.success(teamService.getTeamList());
    }
}
