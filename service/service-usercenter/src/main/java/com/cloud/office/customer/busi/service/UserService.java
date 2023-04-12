package com.cloud.office.customer.busi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.office.customer.busi.common.exception.user.UserExistsException;
import com.cloud.office.customer.busi.common.vo.PageVo;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.UserDto;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.UserPageDto;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import com.cloud.office.customer.busi.service_usercenter.domain.vo.UserVo;

import javax.security.sasl.AuthenticationException;
import java.util.List;


/**
 * 用户服务接口
 *
 * @author feng
 * @date 2019-05-18
 */
public interface UserService extends IService<User> {

    /**
     * 注册
     *
     * @param user 用户信息
     * @return
     * @throws UserExistsException
     */
    void register(User user) throws UserExistsException;

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     * @throws AuthenticationException
     */
    String login(String username, String password) throws AuthenticationException;

    /**
     * 刷新token
     *
     * @param oldToken 旧token
     * @return
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据微信的openId查询用户
     *
     * @param openId 微信的openId
     * @return
     */
    User findByOpenId(String openId);

    /**
     * 获取用户详细信息
     *
     * @param username 用户名
     * @return
     */
    UserVo findUserInfoByUsername(String username);

    /**
     * 获取用户详细信息
     *
     * @param userId 用户编号
     * @return
     */
    UserVo findUserInfoById(Integer userId);

    /**
     * 分页查询所有用户详细信息
     *
     * @param userPageDto 查询条件
     * @return
     */
    PageVo<User> findUserPageList(UserPageDto userPageDto);

    /**
     * 更新用户和角色的对应关系（设置用户拥有的角色）
     *
     * @param userId  用户编号
     * @param roleIds 角色编号集合
     */
    void updateUserRoleRelation(Integer userId, List<Integer> roleIds);

    /**
     * 更新用户信息
     *
     * @param userDto 用户信息
     */
    void updateUser(UserDto userDto);

    /**
     * 新增用户
     *
     * @param userDto 用户信息
     */
    void addUser(UserDto userDto);

    /**
     * 删除指定角色与用户的关联关系
     *
     * @param roleId 角色编号
     */
    void deleteUserRoleRelation(Integer roleId);
}
