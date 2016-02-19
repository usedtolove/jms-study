<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <link href="css/main.css" rel="stylesheet" type="text/css"/>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <title>Chat App</title>
</head>
<body ng-app="chatApp">

    <div ng-controller="chatController" class="container">

        <div>debug: {{messages}}</div>

        <form ng-submit="sendMessage()" name="messageForm">
            <input type="text" placeholder="在此输入新消息" ng-model="message"/>
            <div class="info">
                <span class="count" ng-class="{danger: message.length > max}">你还可以输入{{max - message.length}}个字符</span>
                <button ng-disabled="message.length > max || message.length === 0">发送</button>
            </div>
        </form>
        <hr/>
        <p ng-repeat="message in messages | orderBy:'time':true track by $index" class="message">
            <time>{{message.time | date:'HH:mm'}}</time>
            <span ng-class="{self: message.self}">{{message.message}}</span>
        </p>
    </div>

    <script src="public/bower_components/sockjs/sockjs.js" type="text/javascript"></script>
    <script src="public/bower_components/stomp-websocket/lib/stomp.min.js" type="text/javascript"></script>
    <script src="public/bower_components/angular/angular.js"></script>
    <script src="public/bower_components/lodash/dist/lodash.js"></script>
    <script src="js/chatApp.js" type="text/javascript"></script>
</body>
</html>