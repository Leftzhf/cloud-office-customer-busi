package com.cloud.office.customer.busi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/3/17 17:01
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.cloud.office.customer.busi"})
public class ServiceDictionaryApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceDictionaryApplication.class, args);
    }

}
