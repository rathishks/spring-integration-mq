package com.rathish.spring.integration.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

@MessageEndpoint
@Slf4j
public class InboundMessageListener {

    @ServiceActivator(inputChannel = "inputChannel", outputChannel = "transformerChannel")
    public String receiveMessage(Message<String> message){
        log.info("Message Received  : <{}>", message.getPayload());
        return message.getPayload();
    }

}
