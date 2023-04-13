package com.cloud.office.customer.busi.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.office.customer.busi.common.db.util.PageUtils;
import com.cloud.office.customer.busi.common.exception.user.UserExistsException;
import com.cloud.office.customer.busi.common.exception.user.UserNotExistsException;
import com.cloud.office.customer.busi.common.vo.PageVo;
import com.cloud.office.customer.busi.exception.ApplicationException;
import com.cloud.office.customer.busi.mapper.UserMapper;
import com.cloud.office.customer.busi.service.PermissionService;
import com.cloud.office.customer.busi.service.RoleService;
import com.cloud.office.customer.busi.service.TeamService;
import com.cloud.office.customer.busi.service.UserService;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.UserDto;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.UserPageDto;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.Permission;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.Role;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import com.cloud.office.customer.busi.common.enums.PermissionTypeEnum;
import com.cloud.office.customer.busi.service_usercenter.domain.vo.ButtonVo;
import com.cloud.office.customer.busi.service_usercenter.domain.vo.MenuVo;
import com.cloud.office.customer.busi.service_usercenter.domain.vo.UserVo;
import com.cloud.office.customer.busi.tree.TreeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户服务接口实现
 *
 * @author feng
 * @date 2019-05-18
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private TeamService teamService;

    /**
     * 注册
     *
     * @param user 用户信息
     * @return
     * @throws UserExistsException
     */
    @Override
    public void register(User user) throws UserExistsException {

        if (!StringUtils.isBlank(user.getUsername()) && baseMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUsername, user.getUsername())) != null) {
            throw new UserExistsException(String.format("【%s】用户名已存在", user.getUsername()));
        }
        if (!StringUtils.isBlank(user.getEmail()) && baseMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getEmail, user.getEmail())) != null) {
            throw new UserExistsException(String.format("【%s】邮箱已存在", user.getEmail()));
        }
        // 新增用户
        baseMapper.insert(user);

        Role role = roleService.findUserNormalRole();
        if (role == null || role.getId() == null || role.getId() == 0) {
            throw new RuntimeException("默认角色未找到");
        }
        log.info("新增用户成功,id={}", role.getId());
        List<Integer> roleIds = new ArrayList<>();
        roleIds.add(role.getId());
        // 设置默认角色
        updateUserRoleRelation(user.getId(), roleIds);
    }


    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return
     */
    @Override
    public User findByUsername(String username) {
        User user = baseMapper.selectByUsername(username);
        return  user;
    }

    /**
     * 根据微信的openId查询用户
     *
     * @param openId 微信的openId
     * @return
     */
    @Override
    public User findByOpenId(String openId) {
        return baseMapper.selectByOpenId(openId);
    }

    /**
     * 获取用户详细信息
     *
     * @param username 用户名
     * @return
     */
    @Override
    public UserVo findUserInfoByUsername(String username) {
        User user = findByUsername(username);
        if (user == null) {
            throw new ApplicationException(String.format("【%s】用户不存在", username));
        }
        UserVo userVo = new UserVo(user);
        List<Permission> permissions = permissionService.findAllByUserId(user.getId());
        log.info("获取用户所有权限,{}", permissions);
        List<ButtonVo> buttonVos = new ArrayList<>();
        List<MenuVo> menuVos = new ArrayList<>();
        if (permissions != null && permissions.size() > 0) {
            permissions.forEach(permission -> {
                // 按钮权限
                if (PermissionTypeEnum.BUTTON == permission.getType()) {
                    buttonVos.add(new ButtonVo(
                            permission.getId(),
                            permission.getParentId(),
                            permission.getResources(),
                            permission.getName(),
                            permission.getRemark()
                    ));
                }
                // 菜单权限
                if (PermissionTypeEnum.MENU == permission.getType()) {
                    menuVos.add(new MenuVo(
                            permission.getId(),
                            permission.getParentId(),
                            permission.getPath(),
                            permission.getComponent(),
                            permission.getResources(),
                            permission.getName(),
                            permission.getIcon(),
                            permission.getSort(),
                            permission.getRemark()
                    ));
                }
            });
        }
        userVo.setButtons(buttonVos);
        userVo.setMenus(TreeUtils.findRoots(menuVos));
        log.info("用户信息,{}", userVo);
        return userVo;
    }

    /**
     * 获取用户详细信息
     *
     * @param userId 用户编号
     * @return
     */
    @Override
    public UserVo findUserInfoById(Integer userId) {
        User user = baseMapper.selectById(userId);
        if (user == null) {
            throw new UserNotExistsException("用户不存在");
        }
        UserVo userVo = new UserVo(user);
        // 用户拥有的角色
        userVo.setRoles(roleService.findRoleListByUserId(userId));
        log.info("用户信息,{}", userVo);
        return userVo;
    }

    /**
     * 分页查询所有用户详细信息
     *
     * @param userPageDto 查询条件
     * @return
     */
    @Override
    public PageVo<User> findUserPageList(UserPageDto userPageDto) {
        IPage<User> page = baseMapper.selectPageList(userPageDto);
        return PageUtils.getPageVo(page);
    }

    /**
     * 更新用户和角色的对应关系（设置用户拥有的角色）
     *
     * @param userId  用户编号
     * @param roleIds 角色编号集合
     */
    @Override
    public void updateUserRoleRelation(Integer userId, List<Integer> roleIds) {
        // 删除用户所有角色
        baseMapper.deleteUserRoleByUserId(userId);
        // 编号排序
        roleIds = roleIds.stream().sorted().collect(Collectors.toList());
        // 遍历新增用户的角色
        roleIds.forEach(roleId -> baseMapper.insertUserRole(userId, roleId));
    }

    /**
     * 更新用户信息
     *
     * @param userDto 用户信息
     */
    @Override
    public void updateUser(UserDto userDto) {
        log.info("更新用户,{}", JSON.toJSONString(userDto));
        if (userDto.getUserInfo() == null) {
            throw new RuntimeException("更新用户失败,user=" + userDto.getUserInfo());
        }
        if (userDto.getUserInfo().getId() == null || userDto.getUserInfo().getId() == 0) {
            throw new RuntimeException("更新用户失败,userId=" + userDto.getUserInfo().getId());
        }

        baseMapper.updateById(userDto.getUserInfo());
        if (userDto.getRoleIds() != null && userDto.getRoleIds().size() > 0) {
            updateUserRoleRelation(userDto.getUserInfo().getId(), userDto.getRoleIds());
        }
    }

    /**
     * 新增用户
     *
     * @param userDto 用户信息
     */
    @Override
    public void addUser(UserDto userDto) {
        log.info("新增用户,{}", JSON.toJSONString(userDto));
        if (userDto.getUserInfo() == null) {
            throw new RuntimeException("新增用户失败,user=" + userDto.getUserInfo());
        }

        // 加密用户登录密码
        userDto.getUserInfo().setPassword(passwordEncoder.encode(userDto.getUserInfo().getPassword()));
        // 插入用户记录
        baseMapper.insert(userDto.getUserInfo());
        if (userDto.getUserInfo().getId() == null || userDto.getUserInfo().getId() == 0) {
            throw new RuntimeException("新增用户失败,userId=" + userDto.getUserInfo().getId());
        }

        if (userDto.getRoleNameEns() != null && userDto.getRoleNameEns().size() > 0) {
            userDto.setRoleIds(roleService.getRoleIds(userDto.getRoleNameEns()));
        }
        if (userDto.getRoleIds() != null && userDto.getRoleIds().size() > 0) {
            log.info("新增用户成功,开始更新角色{}", JSON.toJSONString(userDto));
            updateUserRoleRelation(userDto.getUserInfo().getId(), userDto.getRoleIds());
        }
    }

    /**
     * 删除指定角色与用户的关联关系
     *
     * @param roleId 角色编号
     */
    @Override
    public void deleteUserRoleRelation(Integer roleId) {
        baseMapper.deleteUserRoleByRoleId(roleId);
    }
}
