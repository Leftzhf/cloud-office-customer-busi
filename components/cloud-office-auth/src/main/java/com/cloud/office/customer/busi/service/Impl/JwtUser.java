package com.cloud.office.customer.busi.service.Impl;

import com.alibaba.fastjson.annotation.JSONField;
import com.cloud.office.customer.busi.util.EnumValueDeserializer;
import com.cloud.office.customer.busi.enums.UserStatusEnum;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Spring Security需要的用户对象，实现UserDetails接口
 *
 */
@Data
public class JwtUser implements UserDetails {

    /**
     * 用户编号
     */
    private Integer id;

    /**
     * 状态 [0.禁用 1.正常 2.删除]
     */
    @JSONField(serializeUsing = EnumValueDeserializer.class, deserializeUsing = EnumValueDeserializer.class)
    private UserStatusEnum status;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户拥有的权限集合
     */
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser() {
    }

    public JwtUser(Integer id, UserStatusEnum status, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.status = status;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public JwtUser(Integer id, UserStatusEnum status, String username, String password) {
        this.id = id;
        this.status = status;
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status.getValue() == 1;
    }
}
