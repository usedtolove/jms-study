package com.tz.web.si;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.support.Function;
import org.springframework.integration.websocket.ServerWebSocketContainer;
import org.springframework.integration.websocket.outbound.WebSocketOutboundMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.support.MessageBuilder;

import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by hjl on 2016/2/24.
 */
@Configuration
@ImportResource(locations = "classpath:/activemq/amp-hi.xml")
public class SiConfig {

    @Bean(name = "si-hiChannel")
    MessageChannel myRequestChannel() {
        return new DirectChannel();
    }
}
