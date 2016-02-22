package com.tz.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;
import org.springframework.util.Assert;

/**
 * Created by hjl on 2016/2/19.
 */
@Configuration
@PropertySource("classpath:jms-config.properties")
public class JmsConfig {

    @Autowired
    Environment env;

    /**
     *  The ActiveMq connection factory needs to be created
     *  with the url of the broker.
     *
     *  The template is then used in turn to create the Message Sender class:
     */
    @Bean
    public ActiveMQConnectionFactory getConnectionFactory(){
        String jmsUser = env.getProperty("jms.user"); //JMS 用户名
        String jmsPassword = env.getProperty("jms.password"); //JMS 密码
        String jmsUrl = env.getProperty("jms.url"); //JMS 密码
        Assert.notNull(jmsUser);
        Assert.notNull(jmsPassword);
        Assert.notNull(jmsUrl);
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(jmsUser, jmsPassword, jmsUrl);
        return activeMQConnectionFactory;
    }

    /**
     * Create the Spring JMS cached connection to
     * wrap the ActiveMQ connection
     */
    @Bean
    public CachingConnectionFactory getCachingConnectionFactory(){
        CachingConnectionFactory ccf = new CachingConnectionFactory(getConnectionFactory());
        ccf.setSessionCacheSize(10); //cache size.
        return ccf;
    }

    /**
     * Default destination in ActiveMQ
     */
    @Bean
    public ActiveMQQueue getDefaultDestination(){
        String destination = env.getProperty("jms.destination");
        Assert.notNull(destination);
        ActiveMQQueue activeMQQueue = new ActiveMQQueue(destination);
        return activeMQQueue;
    }

    @Bean
    public MessageConverter getMessageConverter(){
        MessageConverter messageConverter = new SimpleMessageConverter();
        return messageConverter;
    }

    /**
     * JmsTemplate instance that uses the cached connection and destination
     */
    @Bean
    public JmsTemplate getJmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(getCachingConnectionFactory());
        jmsTemplate.setDefaultDestination(getDefaultDestination());

        //MessageConverter
        //default is simpleMessageConverter
        SimpleMessageConverter simpleMessageConverter = new SimpleMessageConverter();
        jmsTemplate.setMessageConverter(simpleMessageConverter);

        return jmsTemplate;
    }

}
