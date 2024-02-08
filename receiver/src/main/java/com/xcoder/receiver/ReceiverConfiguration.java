package com.xcoder.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ReceiverConfiguration {
    private static final String QUEUE_1_NAME = "firstQueue";
    private static final String QUEUE_2_NAME = "secondQueue";

    @Bean
    public Queue queue1() {
        return new Queue(QUEUE_1_NAME, false);
    }

    @Bean
    public Queue queue2() {
        return new Queue(QUEUE_2_NAME, false);
    }

    @RabbitListener(queues = QUEUE_1_NAME)
    public void listenQ1(String message) {
        log.info("Message read from firstQueue: {}", message);
    }

    @RabbitListener(queues = QUEUE_2_NAME)
    public void listenQ2(String message) {
        log.info("Message read from secondQueue: {}", message);
    }
}
