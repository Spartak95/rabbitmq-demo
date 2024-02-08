package com.xcoder.sender.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfiguration {
    private static final String QUEUE_1_NAME = "firstQueue";
    private static final String QUEUE_2_NAME = "secondQueue";
    private static final String EXCHANGE_NAME = "testExchange";

    @Bean
    public Queue queue1() {
        return new Queue(QUEUE_1_NAME, false);
    }

    @Bean
    public Queue queue2() {
        return new Queue(QUEUE_2_NAME, false);
    }

    @Bean
    public Exchange exchange() {
        return new TopicExchange(EXCHANGE_NAME, false, false);
    }

    @Bean
    public Binding bindingQ1(Queue queue1, Exchange exchange) {
        return BindingBuilder.bind(queue1).to(exchange).with("first.key").noargs();
    }

    @Bean
    public Binding bindingQ2(Queue queue2, Exchange exchange) {
        return BindingBuilder.bind(queue2).to(exchange).with("second.key").noargs();
    }
}
