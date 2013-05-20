<%@ page pageEncoding="UTF-8"%>

<html>
    <head>
        <title>JSM 点对点 例子</title>
    </head>
    <body>
        <h1>JSM 点对点 例子</h1>
        <hr/>
        <p>一个生产者，对应一个消费者</p>
        <p>生产者:用于产生消息队列</p>
        <p>消费者:消耗消息队列</p>
        <img src="/images/p2p.gif" alt="JSM原理图">
        <br/>
        剧本:
        <p>生产者由<a href="/jsp/register.jsp">用户注册页面</a>担当，每提交一个用户注册请求，都将该消息发送给目的地。
        <br/>
        消费者由一个计划任务担当，到系统时间点到达某个时间后，从目的地中接收消息。</p>
    </body>
</html>