package com.cloud.office.customer.busi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.office.customer.busi.vo.PageVo;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.RoleDto;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.RolePageDto;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.Role;
import com.cloud.office.customer.busi.service_usercenter.domain.vo.RoleVo;

import java.util.List;

/**
 * 角色服务
 *
 * @author feng
 * @date 2019-05-20
 */
public interface RoleService extends IService<Role> {

    /**
     * 更新角色和权限的对应关系（设置角色拥有的权限）
     *
     * @param roleId        角色编号
     * @param permissionIds 权限编号集合
     */
    void updateRolePermissionRelation(Integer roleId, List<Integer> permissionIds);

    /**
     * 通过用户编号查找拥有的角色集合
     *
     * @param userId 用户编号
     * @return
     */
    List<Role> findRoleListByUserId(Integer userId);

    /**
     * 分页查询所有角色
     *
     * @param rolePageDto 查询条件
     * @return
     */
    PageVo<Role> findRolePageList(RolePageDto rolePageDto);

    /**
     * 获取用户默认角色 - 临时
     *
     * @return
     */
    Role findUserNormalRole();

    /**
     * 新增角色
     *
     * @param roleDto 角色信息
     */
    void addRole(RoleDto roleDto);

    /**
     * 删除角色
     *
     * @param roleId 角色编号
     */
    void deleteRole(Integer roleId);

    /**
     * 更新角色信息
     *
     * @param roleDto 角色信息
     */
    void updateRole(RoleDto roleDto);

    /**
     * 获取角色信息，包括角色拥有的权限编号
     *
     * @param roleId 角色编号
     * @return
     */
    RoleVo getRole(Integer roleId);

    /**
     * 获取指定用户的角色级别
     *
     * @param userId 用户编号
     * @return
     */
    Integer getTallestRoleLevel(Integer userId);

    /**
     * 获取角色编号集合
     *
     * @param roleNameEns 角色英文名称集合
     * @return
     */
    List<Integer> getRoleIds(List<String> roleNameEns);
}
