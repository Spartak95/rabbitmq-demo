package com.xcoder.sender.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/send")
@RequiredArgsConstructor
public class SendController {
    private Long counter = 0L;
    private static final String EXCHANGE_NAME = "testExchange";

    private final RabbitTemplate rabbitTemplate;

    @GetMapping("/qe1")
    public ResponseEntity<String> sendMessageToQ1(@RequestParam String message) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "first.key", message);
        Long count = ++counter;
        log.info(message, "{} is send! count send object = {}", count);

        return ResponseEntity.ok().body("Send to Q1!");
    }

    @GetMapping("/qe2")
    public ResponseEntity<String> sendMessageToQ2(@RequestParam String message) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "second.key", message);
        Long count = ++counter;
        log.info(message, "{} is send! count send object = {}", count);

        return ResponseEntity.ok().body("Send to Q2!");
    }
}
