package com.tz.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.util.Assert;

/**
 * Jms Config Class
 * @since 2016-02-23
 * @author HuJingLing
 */
@Configuration
@PropertySource("classpath:jms-config.properties")
//@ImportResource(locations = "classpath:/activemq/amp-config.xml")
public class JmsConfig {

    @Autowired
    Environment env;

    @Bean
    public ActiveMQConnectionFactory getConnectionFactory(){
//        String jmsUser = env.getProperty("jms.user"); //JMS 用户名
//        String jmsPassword = env.getProperty("jms.password"); //JMS 密码
//        String jmsUrl = env.getProperty("jms.url"); //JMS 密码
//        Assert.notNull(jmsUser);
//        Assert.notNull(jmsPassword);
//        Assert.notNull(jmsUrl);
//        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(jmsUser, jmsPassword, jmsUrl);

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        String brokerURL = env.getProperty("jms.brokerURL"); //JMS brokerURL
        Assert.notNull(brokerURL);
        activeMQConnectionFactory.setBrokerURL(brokerURL);
        return activeMQConnectionFactory;
    }

    /**
     * Create ActiveMq connection factory
     * and wrap it with Spring JMS cached connection to
     */
    @Bean(name = "connectionFactory")
    public CachingConnectionFactory getCachingConnectionFactory(){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setTargetConnectionFactory(getConnectionFactory());
        cachingConnectionFactory.setSessionCacheSize(10); //cache size.
        cachingConnectionFactory.setCacheProducers(false);
        return cachingConnectionFactory;
    }

    /**
     * Default destination in ActiveMQ
     * (目标配置为 Queue，此demo不需要 topic)
     */
    @Bean
    public ActiveMQQueue getDefaultDestination(){
        String destination = env.getProperty("jms.destination");
        Assert.notNull(destination);
        ActiveMQQueue activeMQQueue = new ActiveMQQueue(destination);
        return activeMQQueue;
    }

    /**
     * JmsTemplate instance that uses the cached connection and destination
     * to create the Message Sender
     */
    @Bean
    public JmsTemplate getJmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(getCachingConnectionFactory());
        jmsTemplate.setDefaultDestination(getDefaultDestination());
        //default is simpleMessageConverter:
        /*SimpleMessageConverter simpleMessageConverter = new SimpleMessageConverter();
        jmsTemplate.setMessageConverter(simpleMessageConverter);*/
        return jmsTemplate;
    }

}
