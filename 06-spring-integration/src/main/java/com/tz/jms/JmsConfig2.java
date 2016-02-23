//package com.tz.jms;
//
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.apache.activemq.command.ActiveMQQueue;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.ImportResource;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.jms.connection.CachingConnectionFactory;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.util.Assert;
//
///**
// * Jms Config Class
// * @since 2016-02-23
// * @author HuJingLing
// */
//@Configuration
//@PropertySource("classpath:jms-config.properties")
//@ImportResource(locations = "classpath:/META-INF/spring/integration/activemq/cafeDemo-amq-baristaCold-xml.xml")
//public class JmsConfig2 {
//
//    @Autowired
//    Environment env;
//
//    /**
//     * Create ActiveMq connection factory
//     * and wrap it with Spring JMS cached connection to
//     */
//    @Bean
//    public CachingConnectionFactory getConnectionFactory(){
////        String jmsUser = env.getProperty("jms.user"); //JMS 用户名
////        String jmsPassword = env.getProperty("jms.password"); //JMS 密码
////        String jmsUrl = env.getProperty("jms.url"); //JMS 密码
//        String brokerURL = env.getProperty("jms.brokerURL"); //JMS brokerURL
////        Assert.notNull(jmsUser);
////        Assert.notNull(jmsPassword);
////        Assert.notNull(jmsUrl);
//        Assert.notNull(brokerURL);
////        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(jmsUser, jmsPassword, jmsUrl);
//        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
//        activeMQConnectionFactory.setBrokerURL(brokerURL);
//        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
//        cachingConnectionFactory.setTargetConnectionFactory(activeMQConnectionFactory);
//        cachingConnectionFactory.setSessionCacheSize(10); //cache size.
//        cachingConnectionFactory.setCacheProducers(false);
//        return cachingConnectionFactory;
//    }
//
//    /**
//     * Default destination in ActiveMQ
//     * (目标配置为 Queue，此demo不需要 topic)
//     */
//    @Bean
//    public ActiveMQQueue getDefaultDestination(){
//        String destination = env.getProperty("jms.destination");
//        Assert.notNull(destination);
//        ActiveMQQueue activeMQQueue = new ActiveMQQueue(destination);
//        return activeMQQueue;
//    }
//
//    /**
//     * JmsTemplate instance that uses the cached connection and destination
//     * to create the Message Sender
//     */
//    @Bean
//    public JmsTemplate getJmsTemplate(){
//        JmsTemplate jmsTemplate = new JmsTemplate();
//        jmsTemplate.setConnectionFactory(getConnectionFactory());
//        jmsTemplate.setDefaultDestination(getDefaultDestination());
//        //default is simpleMessageConverter:
//        /*SimpleMessageConverter simpleMessageConverter = new SimpleMessageConverter();
//        jmsTemplate.setMessageConverter(simpleMessageConverter);*/
//        return jmsTemplate;
//    }
//
//}
