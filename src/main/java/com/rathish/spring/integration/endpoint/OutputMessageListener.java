package com.rathish.spring.integration.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OutputMessageListener {

    @JmsListener(destination = "DEV.QUEUE.2", containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(String message){
        log.info("Message received in output listener <{}>", message);
    }
}
