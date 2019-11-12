package com.zc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SmartCommunityEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartCommunityEurekaApplication.class, args);
    }

}
