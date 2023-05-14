package com.cloud.office.customer.busi.service_im.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/5/13 17:20
 */
@Data
public class ConversationStateVO {


    State stateByWeek;

    String dateString;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
   public static class State {
        String nickName;

        Integer countConverSationState;
    }
}
