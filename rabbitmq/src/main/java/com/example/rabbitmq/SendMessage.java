package com.example.rabbitmq;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendMessage {

    private final AmqpAdmin amqpAdmin;
    private final AmqpTemplate amqpTemplate;
    private final Queue queue;

    @Autowired
    public SendMessage(AmqpAdmin amqpAdmin, AmqpTemplate amqpTemplate, Queue queue) {
        this.amqpAdmin = amqpAdmin;
        this.amqpTemplate = amqpTemplate;
        this.queue = queue;
    }

    void send(String message) {
        this.amqpTemplate.convertAndSend(this.queue.getName(), message);
    }

}