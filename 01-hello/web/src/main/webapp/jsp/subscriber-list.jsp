<%@ page pageEncoding="UTF-8" %>

<html>
<head>
    <title>JSM 发布&订阅模式示例</title>
    <link rel="stylesheet" href="/css/style.css"/>
    <script src="/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="/js/stomp.js" type="text/javascript"></script>
    <script src="/js/stock-demo.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            myConnect();
        });
    </script>
</head>
<body>
<h1>JSM 发布&订阅模式示例</h1>
<hr/>
<textarea id="mytext" style="display: none"></textarea>
<table id="stockTable" class="stockTable" border="1">
    <thead>
    <tr>
        <th class="symbol">代码</th>
        <th class="name">名称</th>
        <th class="open">今开</th>
        <th class="last">最新价</th>
        <th class="high">最高</th>
        <th class="low">最低</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>
</body>
</html>