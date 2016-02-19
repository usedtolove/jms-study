/**
 * Created by hjl on 2016/2/18.
 */
(function () {
    "use strict";

    var app = angular.module("chatApp", []);

    app.service("chatService", function($q, $timeout){
        console.log("chatService...");
        //console.log("$q:"+$q);
        //console.log("$timeout:"+$timeout);

        var service = {};
        var listener = $q.defer();
        var socket =  {client: null, stomp: null};
        var messageIds = [];

        service.RECONNECT_TIMEOUT = 30000;
        service.SOCKET_URL = "/jmsChat/chat";
        service.CHAT_TOPIC = "/topic/message";
        service.CHAT_BROKER = "/app/chat";

        service.receive = function() {
            return listener.promise;
        };

        service.send = function(message) {
            var id = Math.floor(Math.random() * 1000000);
            socket.stomp.send(service.CHAT_BROKER, {
                priority: 9
            }, JSON.stringify({
                message: message,
                id: id
            }));
            messageIds.push(id);
        };

        var reconnect = function() {
            $timeout(function() {
                initialize();
            }, this.RECONNECT_TIMEOUT);
        };

        var getMessage = function(data) {
            var message = JSON.parse(data), out = {};
            out.message = message.message;
            out.time = new Date(message.time);
            if (_.contains(messageIds, message.id)) {
                out.self = true;
                messageIds = _.remove(messageIds, message.id);
            }
            return out;
        };

        var startListener = function() {
            console.log("startListener...");
            socket.stomp.subscribe(service.CHAT_TOPIC, function(data) {
                listener.notify(getMessage(data.body));
            });
        };

        var initialize = function() {
            console.log("initialize...");
            socket.client = new SockJS(service.SOCKET_URL);
            socket.stomp = Stomp.over(socket.client);
            socket.stomp.connect({}, startListener);
            socket.stomp.onclose = reconnect;
        };

        initialize();
        return service;

    });

    app.controller("chatController", function ($scope, chatService) {
        console.log("chatController...");
        //console.log("chatService:"+chatService);

        //输入的字符
        $scope.message = "";
        //字符上限
        $scope.max = 140;

        //发送消息
        $scope.sendMessage = function () {
            console.log("sendMessage...");
            //todo...
            chatService.send($scope.message);

            chatService.receive().then(null, null, function(message) {
                $scope.messages.push(message);
            });

            //reset
            $scope.message = "";
        };

        //响应消息数组
        $scope.messages = [];

    });

}());
