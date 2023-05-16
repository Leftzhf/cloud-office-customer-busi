package com.cloud.office.customer.busi.service_im.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/5/13 17:20
 */
@Data
public class ConversationStateVO {


    //值对象
    List<State> stateByWeek;

    //周数
    List<String> dateString;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
   public static class State {
        //用户昵称
        String nickName;

        //访问次数list
        List<Integer> countConverSationState;
    }
}
