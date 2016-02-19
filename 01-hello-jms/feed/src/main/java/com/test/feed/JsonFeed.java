package com.test.feed;

import com.google.gson.Gson;
import com.test.entity.Stock;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Date;
import java.util.List;

/**
 * Author: 胡荆陵
 * 发布 JSON 股票消息类
 */
public class JsonFeed{

    private static final String JMS_USER = "user"; //JMS 用户名
    private static final String JMS_PASS = "user"; //JMS 密码
    private static final String JMS_URL = ActiveMQConnection.DEFAULT_BROKER_URL; //JMS URL地址
//    private static final String JMS_URL = "ws://localhost:61614"; //JMS URL地址
    private boolean transacted = true; //是否事务消息
    private String subject = "stock"; //目的地名称
    private List<Stock> stockList;  //股票列表
    private Gson gson; //JSON 工具类

    public static void main(String[] args) {
        JsonFeed jsonFeed = new JsonFeed();
        jsonFeed.stockList = MockData.getData();
        jsonFeed.gson = new Gson();
        jsonFeed.start();
    }

    private void start() {
            TopicConnection connection = null;
            try {
                //连接工厂，用于产生连接
                ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(JMS_USER, JMS_PASS, JMS_URL);
                //创建连接
                connection = connectionFactory.createTopicConnection();
                connection.start();

                //创建会话
                TopicSession pubSession = connection.createTopicSession(transacted, Session.AUTO_ACKNOWLEDGE);
                //创建消息主题
                Topic topic = pubSession.createTopic(subject);
                //创建消息生产者
                TopicPublisher publisher = pubSession.createPublisher(topic);
                publisher.setDeliveryMode(DeliveryMode.PERSISTENT);
                //循环发送消息
                int index = 0;
                while (true){
                    Stock stock = stockList.get(index);
                    stock = mockChange(stock);
                    TextMessage textMessage = pubSession.createTextMessage();
                    String jsonString = gson.toJson(stock);
                    textMessage.setText(jsonString);
                    publisher.publish(textMessage);
                    if(transacted){
                        pubSession.commit();
                    }
                    System.out.println("发送消息: " + jsonString + ", "+new Date());
                    index++;
                    index = index % stockList.size();
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //释放资源
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
    }

    //模拟股票变更
    private Stock mockChange(Stock stock) {
        //涨跌幅度
        double scale = Math.random()*0.1 + 0.1;
        double change = stock.getOpen() * scale;
        //是否涨跌
        boolean isAdd = System.currentTimeMillis()%2 == 1;
        if(!isAdd){
            change = -change;
        }
        //最新价
        double oldLast = stock.getLast();
        double newLast = oldLast + change;
        if(newLast < 0){
            newLast = -newLast;
            change = newLast - oldLast;
        }
        stock.setChange(change);
        stock.setLast(newLast);
        //最高价
        if(newLast > stock.getHigh()){
            stock.setHigh(newLast);
        }
        //最低价
        if(newLast < stock.getLow()){
            stock.setLow(newLast);
        }
        stock.setDate(new Date());
        return stock;
    }
}
