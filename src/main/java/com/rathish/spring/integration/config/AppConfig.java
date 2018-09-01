package com.rathish.spring.integration.config;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;

import javax.jms.JMSException;
import javax.jms.Session;

@Configuration
@ComponentScan("com.rathish.spring.integration")
@ImportResource({"classpath:mq-gateway.xml"})
@EnableIntegration
@Slf4j
public class AppConfig {

    @Bean
    public MQQueueConnectionFactory mqQueueConnectionFactory(@Value("${mq.hostname}") String hostName,
                            @Value("${mq.port}") String port, @Value("${mq.queue.manager}") String queueManager,
                            @Value("${mq.channel}") String channel){
        MQQueueConnectionFactory mqQueueConnectionFactory = new MQQueueConnectionFactory();
        mqQueueConnectionFactory.setHostName(hostName);
        try{
            mqQueueConnectionFactory.setPort(Integer.parseInt(port));
            mqQueueConnectionFactory.setQueueManager(queueManager);
            mqQueueConnectionFactory.setChannel(channel);
            mqQueueConnectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
            mqQueueConnectionFactory.setCCSID(1208);
        } catch (JMSException e) {
            log.error("Error creating MQ Connection factory:", e);
        }
        return mqQueueConnectionFactory;
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory(
            UserCredentialsConnectionFactoryAdapter userCredentialsConnectionFactoryAdapter,
            DefaultJmsListenerContainerFactoryConfigurer configurer){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, userCredentialsConnectionFactoryAdapter);
        factory.setConnectionFactory(userCredentialsConnectionFactoryAdapter);
        factory.setConcurrency("1");
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        factory.setSessionTransacted(false);
        return factory;
    }


}
