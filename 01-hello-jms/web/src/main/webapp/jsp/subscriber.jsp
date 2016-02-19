<%@ page pageEncoding="UTF-8"%>

<html>
    <head>
        <title>JSM 发布&订阅模式示例 22</title>
        <script src="/js/jquery-1.8.3.min.js" type="text/javascript"></script>
        <script src="/js/stomp.js" type="text/javascript"></script>
        <script>
            var url = "ws://localhost:61614/stomp";
            var username = "user";
            var password = "user";
            var destination = "/topic/stock";
            var client ;
            var subsId ;

            $(function(){
//                myConnect();
            });

            //订阅主题
            var subscribe_callback = function(message) {
               //alert("subscribe_callback");
                    $("#mytext").val(message.body);
             };

            function myConnect(){
                client = Stomp.client(url);

                //连接创建成功
                var connect_callback = function(frame) {
                    subsId = client.subscribe(destination, subscribe_callback);
                 };
                //连接创建失败
                var error_callback = function(error) {
//                   alert("连接失败:"+error.headers.message);
                   alert("连接失败:"+error);
                 };
                client.connect(username, password, connect_callback, error_callback);
            }

            function myDisconnect(){
                client.disconnect(function(){
                    alert("已断开连接");
                });
            }
            function mySubscribe(){
                subsId = client.subscribe(destination, subscribe_callback);
            }
            function myUnsubscribe(){
                client.unsubscribe(subsId);
            }
        </script>
    </head>
    <body>
        <h1>JSM 发布&订阅模式示例</h1>
        <hr/>
        <input type="button" value="Connect" onclick="myConnect()">
        <input type="button" value="Disconnect" onclick="myDisconnect()">
        <input type="button" value="Subscribe" onclick="mySubscribe()">
        <input type="button" value="Unsubscribe" onclick="myUnsubscribe()">
        <br/>
        <textarea id="mytext"></textarea>
    </body>
</html>