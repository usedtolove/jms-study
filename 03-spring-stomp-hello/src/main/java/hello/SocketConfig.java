package hello;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

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
        config.enableSimpleBroker("/topic"); //s==>c
        //c==>s,所有 Destination 的前缀，对 @MessageMapping 也发挥作用
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/hello") // full path is /app/hello
                .withSockJS(); //Spring allow fallback and is compatible with SockJS
    }
}
