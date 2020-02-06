package com.zc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableZuulProxy
@RefreshScope
public class GatewayApplication {

    public static void main(String[] args) {
        if (args.length == 0) {
            args = new String[]{"--spring.profiles.active=dev"};
        }
        SpringApplication.run(GatewayApplication.class, args);
    }
}
