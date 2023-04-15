package com.cloud.office.customer.busi.controller;

import com.alibaba.fastjson.JSON;
import com.cloud.office.customer.busi.common.vo.ResultVo;
import com.cloud.office.customer.busi.service.RoleService;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.RoleDto;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.RolePageDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/role")
@Tag(name = "RoleController", description = "角色接口")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 新增角色
     *
     * @param roleDto 角色信息
     * @return
     */
    @PostMapping
    public ResultVo addRole(@RequestBody RoleDto roleDto) {
        roleService.addRole(roleDto);
        return ResultVo.success();
    }

    /**
     * 删除角色
     *
     * @param roleId 角色编号
     * @return
     */
    @DeleteMapping("/{roleId}")
    public ResultVo deleteRole(@PathVariable Integer roleId) {
        roleService.deleteRole(roleId);
        return ResultVo.success();
    }

    /**
     * 更新角色信息
     *
     * @param roleDto 角色信息
     * @return
     */
    @PutMapping
    public ResultVo updateRole(@RequestBody RoleDto roleDto) {
        roleService.updateRole(roleDto);
        return ResultVo.success();
    }

    /**
     * 获取角色信息
     *
     * @param roleId 角色编号
     * @return
     */
    @GetMapping("/{roleId}")
    public ResultVo getRole(@PathVariable Integer roleId) {
        return ResultVo.success(roleService.getRole(roleId));
    }

    /**
     * 查询所有角色信息 - 不分页
     *
     * @return
     */
    @GetMapping
    public ResultVo getRoleList() {
        return ResultVo.success(roleService.list());
    }

    /**
     * 查询所有角色信息 - 分页
     *
     * @param rolePageDto 查询条件
     * @return
     */
    @PostMapping("/list")
    public ResultVo getRoleList(@RequestBody RolePageDto rolePageDto) {
        log.info("获取角色分页数据,{}", JSON.toJSONString(rolePageDto));
        return ResultVo.success(roleService.findRolePageList(rolePageDto));
    }

    /**
     * 更新角色拥有的权限
     *
     * @param roleId        角色编号
     * @param permissionIds 权限编号集合
     * @return
     */
    @PutMapping("/{roleId}/permissions")
    public ResultVo updateRolePermission(@PathVariable Integer roleId, @RequestBody List<Integer> permissionIds) {
        roleService.updateRolePermissionRelation(roleId, permissionIds);
        return ResultVo.success();
    }

    /**
     * 获取指定用户的最高角色级别
     *
     * @param userId 用户编号
     * @return
     */
    @GetMapping("/level/{userId}")
    public ResultVo getTallestRoleLevel(@PathVariable Integer userId) {
        return ResultVo.success(roleService.getTallestRoleLevel(userId));
    }

}
