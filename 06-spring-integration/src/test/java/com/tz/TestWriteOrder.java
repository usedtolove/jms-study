package com.tz;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.samples.cafe.Cafe;
import org.springframework.integration.samples.cafe.DrinkType;
import org.springframework.integration.samples.cafe.Order;

import java.io.IOException;

/**
 * Created by hjl-game on 2016/2/23.
 */
public class TestWriteOrder {

    @Test
    public void test1() throws IOException {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext(
                "/activemq/amp-conn.xml",
                "/activemq/amp-write.xml");

        Cafe cafe = (Cafe) context.getBean("cafe");
        for (int i = 1; i <= 10; i++) {
            Order order = new Order(i);
            order.addItem(DrinkType.LATTE, 2, false);
//            order.addItem(DrinkType.MOCHA, 3, true);
            System.out.println("place order:"+order);
            cafe.placeOrder(order);
        }

        context.close();
    }

}
