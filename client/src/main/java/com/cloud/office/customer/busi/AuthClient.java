package com.cloud.office.customer.busi;

import com.cloud.office.customer.busi.common.vo.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@FeignClient(name = "cloud-office-auth")
public interface AuthClient {
    @PostMapping("/validateToken")
    ResultVo validateToken(HttpServletRequest request);
}
