package com.cloud.office.customer.busi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author ZuoHaoFan
 * @Description: new java files header..
 * @date 2023/3/16 10:56
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceIMApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceIMApplication.class, args);
    }
}
