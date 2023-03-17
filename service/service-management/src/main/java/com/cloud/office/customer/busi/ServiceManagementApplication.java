package com.cloud.office.customer.busi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/3/16 10:59
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceManagementApplication.class, args);
    }
}
