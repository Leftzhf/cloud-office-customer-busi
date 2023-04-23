package com.cloud.office.customer.busi.service_usercenter.domain.vo;

import com.cloud.office.customer.busi.service_usercenter.domain.entity.Role;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 用户VO
 *
 */
@Data
@NoArgsConstructor
public class UserVo implements Serializable {

    private static final long serialVersionUID = 2862151578750911342L;
    /**
     * 用户信息
     */
    private User userInfo;

    /**
     * 用户拥有的角色集合
     */
    private List<Role> roles;

    /**
     * 用户拥有的按钮权限集合
     */
    private List<ButtonVo> buttons;

    /**
     * 用户拥有的菜单权限集合
     */
    private List<MenuVo> menus;

    public UserVo(User userInfo) {
        this.userInfo = userInfo;
    }
}
