package hello;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by hjl on 2016/2/19.
 */
@Controller
public class GreetingController {

    @MessageMapping("/hello") //mapping destination "/app/hello" (Endpoint)
    @SendTo("/topic/greetings") //broadcast to all subscribers to "/topic/greetings" (Broker)
    public Greeting greeting(HelloMessage message) throws InterruptedException {
        Thread.sleep(3*1000);
        String result = "Hello, " + message.getName();
        return new Greeting(result);
    }

}
