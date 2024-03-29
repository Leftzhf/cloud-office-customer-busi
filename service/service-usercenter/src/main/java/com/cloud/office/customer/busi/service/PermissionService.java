package com.cloud.office.customer.busi.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.Permission;

import java.util.List;

/**
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 根据用户编号，查询所有权限
     *
     * @param userId 用户编号
     * @return
     */
    List<Permission> findAllByUserId(Integer userId);

    /**
     * 根据角色编号，查找所有权限
     *
     * @param roleId
     * @return
     */
    List<Permission> findAllTreeByRoleId(Integer roleId);

    /**
     * 根据角色编号，查找所有权限
     *
     * @param roleId
     * @return
     */
    List<Permission> findAllByRoleId(Integer roleId);

    /**
     * 根据角色编号，查询所有权限ID
     *
     * @param roleId 角色编号
     * @return
     */
    List<Integer> findIdsByRoleId(Integer roleId);

    /**
     * 查询所有权限，以树节点返回。children使用
     *
     * @return
     */
    List<Permission> findAllTree();

    /**
     * 查询所有权限菜单，以树节点返回。children使用
     *
     * @return
     */
    List<Permission> findAllMenusTree();

    /**
     * 删除权限
     *
     * @param permissionId 权限编号
     */
    void deletePermission(Integer permissionId);

    /**
     * 新增权限
     *
     * @param permission 权限对象
     */
    void addPermission(Permission permission);

    /**
     * 修改权限
     *
     * @param permission 权限对象
     */
    void updatePermission(Permission permission);
}
