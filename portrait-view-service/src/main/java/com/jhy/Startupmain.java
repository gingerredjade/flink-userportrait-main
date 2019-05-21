package com.jhy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**'
 * 启动主类
 *
 * Created by JHy on 2019/05/16.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableDiscoveryClient
public class Startupmain {
    public static void main(String[] args) {
        SpringApplication.run( Startupmain.class, args );
    }
}
