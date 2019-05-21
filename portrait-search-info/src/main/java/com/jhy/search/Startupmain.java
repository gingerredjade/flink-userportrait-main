package com.jhy.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 启动主类
 *
 * Created by JHy on 2019/5/20.
 */
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableEurekaClient
public class Startupmain {
    public static void main(String[] args) {
        SpringApplication.run( Startupmain.class, args );
    }
}
