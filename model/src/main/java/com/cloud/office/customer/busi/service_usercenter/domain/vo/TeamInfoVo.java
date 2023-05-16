package com.cloud.office.customer.busi.service_usercenter.domain.vo;

import com.cloud.office.customer.busi.service_usercenter.domain.entity.Team;
import com.cloud.office.customer.busi.service_usercenter.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/5/16 08:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamInfoVo implements Serializable {

    Team team;

    List<User> userList;
}
