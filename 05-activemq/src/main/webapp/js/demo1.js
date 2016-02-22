/**
 * Created by hjl on 2016/2/22.
 * https://simplesassim.wordpress.com/2016/02/02/how-to-receive-a-message-from-an-apache-activemq-queue-with-javascript/
 */
(function () {
    "use strict";

    var app = angular.module("demo", []);

    app.controller("myController", function ($scope) {
        console.log("myController...");
        //响应消息数组
        $scope.messages = ["a","b","c"];

        //new WebSocket
        var webSocket = new WebSocket('ws://localhost:61614', 'stomp');
        //onopen
        webSocket.onopen = function () {
            console.log("on open");
            webSocket.send('CONNECT\n\n\0');
            webSocket.send('SUBSCRIBE\ndestination:message\n\nack:auto\n\n\0');
        };

        webSocket.onmessage = function (e) {
            if (e.data.startsWith('MESSAGE')){
                var msg = e.data;
                console.log("msg:"+msg +" on "+Date.now());

                var html = document.getElementById("myDiv").innerHTML;
                html += msg;
                document.getElementById("myDiv").innerHTML = html;
            }
        };
    });
}());
