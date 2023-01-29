package com.polligonalapps.springexampledlx.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class RabbitService {
    private static final Logger log = LoggerFactory.getLogger(RabbitService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${exchange.name}")
    private String exchange;

    @Value("${queue.key}")
    private String key;


    public void enviarMensagemParaAFila(String mensagemEnviada) {
    	log.info("Enviando mensagem para a fila %s: %s", key, mensagemEnviada);
        rabbitTemplate.convertAndSend(exchange,key,mensagemEnviada);
    }

    @RabbitListener(queues = {"${queue.name}"})
    public void receberMensagem(@Payload String mensagemRecebida){
        log.info("Recebendo mensagem de %s: %s", key, mensagemRecebida);
    }
}
