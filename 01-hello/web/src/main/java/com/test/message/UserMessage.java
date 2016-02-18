package com.test.message;

import com.test.entity.User;

/**
 * User: 胡荆陵
 * Date: 12-12-13
 * User 消息接口类
 */
public interface UserMessage {
    /**
     * 发送User消息
     * @param user User 实例
     */
    void send(User user);

    /**
     * 处理用户队列
     */
    void processUserQueue();

}
