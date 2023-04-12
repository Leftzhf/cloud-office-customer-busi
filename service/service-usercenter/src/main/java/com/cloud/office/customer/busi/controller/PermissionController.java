package com.cloud.office.customer.busi.controller;


import com.cloud.office.customer.busi.common.vo.ResultVo;
import com.cloud.office.customer.busi.service.PermissionService;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 新增权限
     *
     * @param permission 权限信息
     * @return
     */
    @PostMapping
    public ResultVo addPermission(@RequestBody Permission permission) {
        permissionService.addPermission(permission);
        return ResultVo.success();
    }

    /**
     * 删除权限
     *
     * @param permissionId 权限编号
     * @return
     */
    @DeleteMapping("/{permissionId}")
    public ResultVo deletePermission(@PathVariable Integer permissionId) {
        permissionService.deletePermission(permissionId);
        return ResultVo.success();
    }

    /**
     * 修改权限
     *
     * @param permission 权限信息
     * @return
     */
    @PutMapping
    public ResultVo updatePermission(@RequestBody Permission permission) {
        permissionService.updatePermission(permission);
        return ResultVo.success();
    }

    /**
     * 获取权限
     *
     * @param permissionId 权限编号
     * @return
     */
    @GetMapping("/{permissionId}")
    public ResultVo getPermission(@PathVariable Integer permissionId) {
        return ResultVo.success(permissionService.getById(permissionId));
    }

    /**
     * 获取所有权限的树结构
     *
     * @return
     */
    @GetMapping("/tree")
    public ResultVo getPermissionList() {
        return ResultVo.success(permissionService.findAllTree());
    }

    /**
     * 获取所有权限菜单的树结构
     *
     * @return
     */
    @GetMapping("/menus")
    public ResultVo getMenus() {
        return ResultVo.success(permissionService.findAllMenusTree());
    }

}
