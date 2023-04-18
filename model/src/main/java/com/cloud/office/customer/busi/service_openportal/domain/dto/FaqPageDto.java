package com.cloud.office.customer.busi.service_openportal.domain.dto;

import com.cloud.office.customer.busi.db.dto.PageDto;
import com.cloud.office.customer.busi.service_openportal.domain.entity.Faq;
import lombok.Data;

import java.io.Serializable;


@Data
public class FaqPageDto extends PageDto<Faq> implements Serializable {
    private static final long serialVersionUID = -8559844664209546046L;

    /**
     * 标题
     */
    private String title;
}
