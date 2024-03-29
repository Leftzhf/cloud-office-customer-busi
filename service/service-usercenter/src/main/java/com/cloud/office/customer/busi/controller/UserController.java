package com.cloud.office.customer.busi.controller;


import com.alibaba.fastjson.JSON;
import com.cloud.office.customer.busi.vo.ResultVo;
import com.cloud.office.customer.busi.service.UserService;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.RegisterUserDto;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.UserDto;
import com.cloud.office.customer.busi.service_usercenter.domain.dto.UserPageDto;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import com.cloud.office.customer.busi.service_usercenter.domain.vo.UserVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@Tag(name = "UserController", description = "用户接口")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 非关联查询
     *
     * @param id id
     * @return {@link User}
     */
    @PostMapping ("/getById")
    User getByIdUser(@RequestParam Integer id){
        return userService.getById(id);
    }
    @PostMapping("/findUserByUsername")
    User findByUsername(@RequestParam String username){
        return userService.findByUsername(username);
    }

    @PostMapping("/register")
    void register(@RequestBody RegisterUserDto registerUserDto){
        User user = new User();
        user.setUsername(registerUserDto.getUsername());
        user.setPassword(registerUserDto.getPassword());
        user.setEmail(registerUserDto.getEmail());
        user.setNickname(registerUserDto.getNickname());
        userService.register(user);
    }

    /**
     * 关联查询
     *
     * @param userId 用户id
     * @return {@link User}
     */
    @GetMapping("/getUserById/{userId}")
    public User getUserById(@PathVariable Integer userId) {
        log.info("获取用户信息,userId={}", userId);
        User user = userService.getUserById(userId);
        return user;
    }
    /**
     * 新增用户 userDto={"roleIds":[1],"userInfo":{"email":"18727365789@163.com","gender":"2","nickname":"dfdfdfdf","username":"dsfadf"}}
     *
     * @param userDto 用户信息
     * @return
     */
    @PostMapping
    public ResultVo addUser(@RequestBody UserDto userDto) {
        log.info("创建用户,userDto={}", JSON.toJSONString(userDto));
        userService.addUser(userDto);
        return ResultVo.success();
    }

    /**
     * 删除用户
     *
     * @param userId 用户编号
     * @return
     */
    @DeleteMapping("/{userId}")
    public ResultVo deleteUser(@PathVariable Integer userId) {
        log.info("删除用户,userId={}", userId);
        if (ObjectUtils.isEmpty(userId) || userId == 0) {
            return ResultVo.error400("用户编号为空", null);
        }
        return ResultVo.success();
    }

    /**
     * 更新用户信息
     *
     * @param userDto 用户信息
     * @return
     */
    @PutMapping
    public ResultVo updateUser(@RequestBody UserDto userDto) {
        log.info("更新用户信息,userDto={}", JSON.toJSONString(userDto));
        userService.updateUser(userDto);
        return ResultVo.success();
    }

    /**
     * 获取用户信息
     *
     * @param userId 用户编号
     * @return
     */
    @GetMapping("/{userId}")
    public ResultVo getUserInfo(@PathVariable Integer userId) {
        log.info("获取用户信息,userId={}", userId);
        if (ObjectUtils.isEmpty(userId) || userId == 0) {
            return ResultVo.error400("用户编号为空", null);
        }
        UserVo userVo = userService.findUserInfoById(userId);
        return ResultVo.success(userVo);
    }

    /**
     * 获取用户分页数据
     *
     * @param userPageDto 分页查询条件
     * @return
     */
    @PostMapping("/list")
    public ResultVo getUserPageList(@RequestBody UserPageDto userPageDto) {
//        log.info("获取用户分页数据,{}", JSON.toJSONString(userPageDto));
        return ResultVo.success(userService.findUserPageList(userPageDto));
    }

    @PostMapping("/list/server")
    public ResultVo getUserPageServerList(@RequestBody UserPageDto userPageDto) {
//        log.info("获取用户分页数据,{}", JSON.toJSONString(userPageDto));
        return ResultVo.success(userService.findUserPageServerList(userPageDto));
    }
    /**
     * 更新用户拥有的角色
     *
     * @param userId  用户编号
     * @param roleIds 角色编号集合
     * @return
     */
    @PutMapping("/{userId}/roles")
    public ResultVo updateUserRole(@PathVariable Integer userId, @RequestBody List<Integer> roleIds) {
        log.info("修改用户角色,userId={},roleIds={}", userId, roleIds);
        userService.updateUserRoleRelation(userId, roleIds);
        return ResultVo.success();
    }

    /**
     * 获取用户拥有的角色
     *
     * @param userId 用户编号
     * @return
     */
    @GetMapping("/{userId}/roles")
    public ResultVo roleInfo(@PathVariable Integer userId) {
        return ResultVo.success();
    }
    @GetMapping("/info")
    public ResultVo getUserInfo(@RequestHeader("User-Agent") String userName) {
        log.info("获取请求头用户信息,userName:{}",userName);
        UserVo userVo = userService.findUserInfoByUsername(userName);
        return ResultVo.success(userVo);
    }

    @PostMapping("/getUserByRole")
    public ResultVo getUserByRole(@RequestParam Integer level) {
        log.info("获取角色用户,level:{}",level);
        List<User> userByRole = userService.getUserByRole(level);
        return ResultVo.success(userByRole);
    }
}
