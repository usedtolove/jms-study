<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <link href="css/main.css" rel="stylesheet" type="text/css"/>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>

    <script src="public/bower_components/sockjs/sockjs.js" type="text/javascript"></script>
    <script src="public/bower_components/stomp-websocket/lib/stomp.js" type="text/javascript"></script>
    <script src="public/bower_components/angular/angular.js"></script>
    <script src="js/demo2.js" type="text/javascript"></script>

    <title>queue demo</title>
</head>
<body ng-app="demo">
    <h1>activemq queue demo2</h1>
    <hr/>

    <div ng-controller="myController" class="container">
        <button ng-click="demo()">test btn</button>
        <div>debug: {{messages}}</div>
        <ul>
            <li ng-repeat="message in messages track by $index" class="message">
                {{message}}
            </li>
        </ul>
        <div id="myDiv"></div>
    </div>
</body>
</html>