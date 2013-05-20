package com.test.servlet;

import com.test.entity.User;
import com.test.message.UserMessage;
import com.test.message.UserMessageImpl;

import java.io.IOException;
import java.util.Date;

/**
 * User: 胡荆陵
 * Date: 12-12-13
 * 处理用户注册请求的 servlet
 */
public class UserRegisterServlet extends javax.servlet.http.HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        //接收参数
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        //封装对象
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        //发送消息
        UserMessage userMessage = new UserMessageImpl();
        userMessage.send(user);
        //返回用户注册页面
        request.setAttribute("result","消息发送成功" + new Date());
        request.getRequestDispatcher("/jsp/register.jsp").forward(request,response);
    }

}
