package com.rathish.spring.integration.endpoint;

import com.ancientprogramming.fixedformat4j.format.FixedFormatManager;
import com.ancientprogramming.fixedformat4j.format.impl.FixedFormatManagerImpl;
import com.rathish.spring.integration.model.InputObject;
import com.rathish.spring.integration.model.OutputObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

@MessageEndpoint
@Slf4j
public class MessageTransformer {

    @ServiceActivator(inputChannel = "transformerChannel", outputChannel = "outputChannel")
    public String transformMessage(Message<String> message){
        FixedFormatManager fixedFormatManager = new FixedFormatManagerImpl();
        InputObject inputObject = fixedFormatManager.load(InputObject.class, message.getPayload());
        OutputObject outputObject = new OutputObject();
        outputObject.setAccountNumber(inputObject.getAccountNumber());
        outputObject.setPaymentDate(inputObject.getPaymentDate());
        outputObject.setPaymentAmount(inputObject.getPaymentAmount());
        String transformedMessage = fixedFormatManager.export(outputObject);
        log.info("Transformed Message <{}>", transformedMessage);
        return transformedMessage;
    }
}
