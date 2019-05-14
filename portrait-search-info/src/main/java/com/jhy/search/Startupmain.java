package com.jhy.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 启动主类
 *
 * Created by JHy on 2019/5/16.
 */
@SpringBootApplication
@EnableEurekaClient
public class Startupmain {
    public static void main(String[] args) {

        SpringApplication.run( Startupmain.class, args );
    }
}
