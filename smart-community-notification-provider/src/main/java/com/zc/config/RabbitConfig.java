package com.zc.config;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 小帅气
 * @create 2020-02-20-19:41
 */
@Configuration
@EnableRabbit
public class RabbitConfig {
//    @Value("${spring.rabbit.host}")
//    private String host;
//    @Value("${spring.rabbit.port}")
//    private int port;
//    @Value("${spring.rabbit.username}")
//    private String userName;
//    @Value("${spring.rabbit.password}")
//    private String password;
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory factory = new CachingConnectionFactory();
//        factory.setHost(host);
//        factory.setPort(port);
//        factory.setUsername(userName);
//        factory.setPassword(password);
//        factory.setVirtualHost("/smart-community");
//        return factory;
//    }
//    @Bean
//    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
//        return new RabbitAdmin(connectionFactory);
//    }
    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
