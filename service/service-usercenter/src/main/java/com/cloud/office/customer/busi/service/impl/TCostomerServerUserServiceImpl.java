package com.cloud.office.customer.busi.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloud.office.customer.busi.mapper.TCostomerServerRoleRuleMapper;
import com.cloud.office.customer.busi.mapper.TCostomerServerRuleMapper;
import com.cloud.office.customer.busi.mapper.TCostomerServerUserMapper;
import com.cloud.office.customer.busi.mapper.TCostomerServerUserRoleMapper;
import com.cloud.office.customer.busi.service.ITCostomerServerUserService;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.office.customer.busi.service_usercenter.domain.vo.RegisterBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author leftleft
 * @since 2023-03-17
 */
@Service
public class TCostomerServerUserServiceImpl extends ServiceImpl<TCostomerServerUserMapper, TCostomerServerUser> implements ITCostomerServerUserService {

    @Autowired
    private TCostomerServerUserMapper tCostomerServerUserMapper;

    @Autowired
    private TCostomerServerUserRoleMapper tCostomerServerUserRoleMapper;
    @Autowired
    private TCostomerServerRoleRuleMapper tCostomerServerRoleRuleMapper;
    @Autowired
    TCostomerServerRuleMapper tCostomerServerRuleMapper;
    @Override
    public TCostomerServerUser getUserByName(String username) {
        LambdaQueryWrapper<TCostomerServerUser> wrapper = new QueryWrapper<TCostomerServerUser>().lambda();
        wrapper.eq(TCostomerServerUser::getUsername,username);
        return  tCostomerServerUserMapper.selectOne(wrapper);
    }

    @Override
    public Map<String, List<TCostomerServerRule>> getRuleByUserId(String userId) {

        LambdaQueryWrapper<TCostomerServerUserRole> lambda1 = new QueryWrapper<TCostomerServerUserRole>().lambda();
        lambda1.eq(TCostomerServerUserRole::getUserId,userId);
        List<TCostomerServerUserRole> tCostomerServerUserRoles = tCostomerServerUserRoleMapper.selectList(lambda1);
        Map<String, List<TCostomerServerRule>> rulesMap  = new HashMap<>();
        tCostomerServerUserRoles.forEach(item->{
            LambdaQueryWrapper<TCostomerServerRoleRule> roleLambda = new LambdaQueryWrapper<>();
            //获取每个用户对应的角色
            roleLambda.eq(TCostomerServerRoleRule::getRoleId,item.getRoleId());
            List<TCostomerServerRoleRule> tCostomerServerRoleRules = tCostomerServerRoleRuleMapper.selectList(roleLambda);
            //获取每个角色对应的权限列表
            LambdaQueryWrapper<TCostomerServerRule> queryWrapper = new LambdaQueryWrapper<>();
//            queryWrapper.eq(TCostomerServerRule::getId,tCostomerServerRoleRules.get(0).getRuleId());

            List<TCostomerServerRule> ruleList = tCostomerServerRuleMapper.selectList(queryWrapper);
            rulesMap.put(item.getRoleId(),ruleList);
        });

        return rulesMap;

    }

    @Override
    public Integer registerUserInfo( TCostomerServerUser userInfo) {
        int insert = tCostomerServerUserMapper.insert(userInfo);
        return insert;
    }
}
