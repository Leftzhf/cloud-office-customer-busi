package com.cloud.office.customer.busi.jwt;

import com.cloud.office.customer.busi.ServiceUsercenterClient;
import com.cloud.office.customer.busi.service.Impl.JwtUser;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * jwt用户服务impl
 *
 * @author leftleft
 * @date 2023/04/16
 */
@Service
public class JwtUserServiceImpl implements UserDetailsService {

    @Autowired
    private ServiceUsercenterClient usercenterClient;

    /**
     * JWT通过用户名加载用户
     *
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       //从用户中心查用户名
        User user = usercenterClient.findByUsername(username);
        if (user == null || StringUtils.isEmpty(user.getId())) {
            throw new UsernameNotFoundException(String.format("【%s】用户不存在", username));
        }
        // 这里返回的JwtUser还不包含authorities
        return new JwtUser(user.getId(), user.getStatus(), user.getUsername(), user.getPassword());
    }
}
