<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <link href="css/main.css" rel="stylesheet" type="text/css"/>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>

    <script src="public/bower_components/angular/angular.js"></script>
    <script src="js/demo1.js" type="text/javascript"></script>

    <title>queue demo</title>
</head>
<body ng-app="demo">
    <h1>activemq queue demo1</h1>
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