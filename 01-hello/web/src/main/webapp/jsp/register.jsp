<%@ page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>用户注册</title>
    </head>
    <body>
        <h1>用户注册</h1>
        <hr/>
        <span style="color: red">${result}</span>
        <form method="post" action="/user/register">
        <table border="1">
            <tr>
                <th>用户名</th>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <th>密码</th>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                 <td colspan="2" style="text-align: center;">
                     <input type="submit" value="提交">
                 </td>
            </tr>
        </table>
        </form>
    </body>
</html>