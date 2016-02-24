package com.tz.web.socket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

/**
 * Created by hjl on 2016/2/18.
 */
@Configuration
@EnableWebSocketMessageBroker
@ComponentScan
public class SocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    //where communication enters and leaves
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat")
                .withSockJS(); //Spring allow fallback and is compatible with SockJS
    }

    @Bean
    public DefaultHandshakeHandler handshakeHandler() {
//        WebSocketPolicy policy = new WebSocketPolicy(WebSocketBehavior.SERVER);
//        policy.setInputBufferSize(8192);
//        policy.setIdleTimeout(600000);
        return new DefaultHandshakeHandler();
//        return new DefaultHandshakeHandler(
//                new TomcatRequestUpgradeStrategy();
//                new JettyRequestUpgradeStrategy(new WebSocketServerFactory(policy)));
    }

}
