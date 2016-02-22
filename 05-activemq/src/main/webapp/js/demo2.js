/**
 * Created by hjl on 2016/2/22.
 * https://dzone.com/articles/easy-messaging-stomp-over
 */
(function () {
    "use strict";

    var app = angular.module("demo", []);

    app.service("myService", function ($q) {
        console.log("myService...");

        var service = {};
        var listener = $q.defer();

        service.receive = function() {
            return listener.promise;
        };

        //IP address or host name
        var client = Stomp.client( "ws://localhost:61614/stomp", "v11.stomp" );

        var startListener = function() {
            console.log("startListener...");
            client.subscribe("message",
                function( message ) {
                    var data = message.body;
                    var jsonObj = JSON.parse(data);
                    listener.notify(jsonObj);
                }
            );
        };

        //init
        client.connect({}, startListener);

        return service;
    });

    app.controller("myController", function ($scope, myService) {
        console.log("myController...");
        console.log("$scope:"+$scope);
        console.log("myService:"+myService);

        //响应消息数组
        $scope.messages = ["a","b","c"];

        myService.receive().then(null, null, function(message) {
            $scope.messages.push(message);
        });
    });
}());
