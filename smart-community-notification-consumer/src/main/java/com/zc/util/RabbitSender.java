package com.zc.util;

import com.zc.pojo.ActivityRegistration;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @author 小帅气
 * @create 2020-02-29-11:13
 */
@Service
public class RabbitSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 用于监听Server端给我们返回的确认请求,消息到了exchange，ack 就返回true
     */
    private final RabbitTemplate.ConfirmCallback  confirmCallback = (correlationData, ack, cause) -> {
        System.out.println("correlationData:" + correlationData);
        System.out.println("ack:" + ack);
        if (!ack){
            System.out.println("补偿处理...");
        }
    };

    /**
     * 监听对不可达的消息进行后续处理;
     * 不可达消息：指定的路由key路由不到。
     */
    private final RabbitTemplate.ReturnCallback returnCallback = (message, replyCode, replyText,
                                                                  exchange, routingKey) -> System.out.println("return exchange:" + exchange + ", routingKey:" + routingKey +
            ", replyText:" + replyText);

    /**
     * 发送消息
     * @param
     */
    public void sendOrder(ActivityRegistration activityRegistration) {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        CorrelationData cd = new CorrelationData();
        // 消息唯一标识
        cd.setId(UUID.randomUUID().toString().replace("-","") + DateUtils.formatDate(new Date(), "yyyyMMdd"));
        rabbitTemplate.convertAndSend("exchange-1", "sm.activity", activityRegistration, cd);
    }
}
