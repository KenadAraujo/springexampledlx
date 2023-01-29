package com.polligonalapps.springexampledlx.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    
    @Value("${exchange.name}")
    private String exchangeName;

    @Value("${exchange.name.error}")
    private String dlxExchangeName;

    @Value("${queue.name}")
    private String fila;

    @Value("${queue.error}")
    private String filaError;

    @Value("${queue.key}")
    private String key;

    @Value("${queue.key.error}")
    private String error;

    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializaAdmin(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }

    @Bean
    public RabbitAdmin getConnection(ConnectionFactory conn){
        return new RabbitAdmin(conn);
    }

    @Bean
    public DirectExchange exchange(){
        return ExchangeBuilder.directExchange(exchangeName).durable(true).build();
    }

    @Bean
    public DirectExchange dlqExchange(){
        return ExchangeBuilder.directExchange(dlxExchangeName).durable(true).build();
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(fila).deadLetterExchange(dlxExchangeName).deadLetterRoutingKey(error).build();
    }

    @Bean
    public Queue queueError(){
        return QueueBuilder.durable(filaError).build();
    }

    @Bean
    public Binding bindQueue(){
        return BindingBuilder.bind(queue()).to(exchange()).with(key);
    }
    @Bean
    public Binding bindQueueError(){
        return BindingBuilder.bind(queueError()).to(dlqExchange()).with(error);
    }

}
