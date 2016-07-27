package com.example.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        SendMessage sendMessage = (SendMessage) ctx.getBean("sendMessage");
        sendMessage.send("Hello, Jancsi!");

        ReceiveMessage receiveMessage = (ReceiveMessage) ctx.getBean("receiveMessage");
        System.out.println("Received: " + receiveMessage.receive());
    }

}