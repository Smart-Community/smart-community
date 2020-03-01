package com.zc.message.consumer;

import com.rabbitmq.client.Channel;
import com.zc.pojo.ActivityRegistration;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * @author 小帅气
 * @create 2020-02-29-11:36
 */
@Service
public class RabbitConsumer {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "queue-1", durable = "true"),
            exchange = @Exchange(value = "exchange-1",
                    durable = "true", type = "topic",
                    ignoreDeclarationExceptions = "true"),
            key = "sm.activity")
    )
    @RabbitHandler
    public void onOrderMessage(@Payload ActivityRegistration activityRegistration, @Headers Map<String, Object> properties, Channel channel) throws IOException {
        System.out.println("消费端 activityRegistration:" + activityRegistration);
        //todo 进行活动报名操作


        // deliveryTag: 确认消息的条数，一般为1
        Long deliveryTag = (Long) properties.get(AmqpHeaders.DELIVERY_TAG);
        System.out.println("deliveryTag:" + deliveryTag);
        // 限流处理：消息体大小不限制，每次限制消费一条，只作用于该Consumer层，不作用于Channel
        channel.basicQos(0, 1, false);
        // 手工ACK,不批量ack
        channel.basicAck(deliveryTag, false);
    }
}
