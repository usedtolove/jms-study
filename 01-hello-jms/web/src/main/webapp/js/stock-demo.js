var url = "ws://localhost:61614/stomp";
var username = "user";
var password = "user";
var destination = "/topic/stock";
var client;
var subsId;
var stockArray = []; //股票数组

//订阅主题
var subscribe_callback = function (message) {
    $("#mytext").val(message.body);
    var stock = JSON.parse(message.body);
    var exist = $.inArray(stock.symbol, stockArray);
    if (exist >= 0) {
        updateStock(stock);
    } else {
        addStockToTable(stock);
    }
};

//在表格中添加行
function addStockToTable(stock) {
    stockArray.push(stock.symbol);//将股票添加至数组
    //股票各属性值
    var symbol = stock.symbol;
    var name = stock.name;
    var open = stock.open.toFixed(2);
    var last = stock.last.toFixed(2);
    var high = stock.high.toFixed(2);
    var low = stock.low.toFixed(2);
    var change = stock.change.toFixed(2);
    //插入一行TR
    var stockRowIndex = stockTable.rows.length;
    var stockRow = stockTable.insertRow(stockRowIndex);
    stockRow.id = "stock-" + symbol;
    for (var cell = 0; cell <= 5; cell++) {
        stockRow.insertCell(cell);
    }
    //赋值给各TD
    stockRow.cells[0].innerHTML = symbol;
    stockRow.cells[1].innerHTML = name;
    stockRow.cells[2].innerHTML = open;
    stockRow.cells[3].innerHTML = last;
    stockRow.cells[4].innerHTML = high;
    stockRow.cells[5].innerHTML = low;
    //添加样式
    if (parseFloat(last) > parseFloat(open)) {
        stockRow.cells[3].className = 'ups';
    } else if (last < open) {
        stockRow.cells[3].className = 'downs';
    }
}

//将更新表格股票信息
function updateStock(stock) {
    //更新数据
    var symbol = stock.symbol;
    var name = stock.name;
    var open = stock.open.toFixed(2);
    var last = stock.last.toFixed(2);
    var high = stock.high.toFixed(2);
    var low = stock.low.toFixed(2);
    //添加样式
    var className1;
    if (parseFloat(last) > parseFloat(open)) {
        className1 = 'ups';
    } else if (last < open) {
        className1 = 'downs';
    }
    //更新TD
    $("#stock-" + stock.symbol).replaceWith("<tr id=\"stock-" + symbol + "\">" +
        "<td>" + symbol + "</td>" +
        "<td>" + name + "</td>" +
        "<td>" + open + "</td>" +
        "<td class='" + className1 + "'>" + last + "</td>" +
        "<td>" + high + "</td>" +
        "<td>" + low + "</td>" +
        "</tr>");
}

function myConnect() {
    client = Stomp.client(url);

    //连接创建成功
    var connect_callback = function (frame) {
        subsId = client.subscribe(destination, subscribe_callback);
    };
    //连接创建失败
    var error_callback = function (error) {
        alert("连接失败:" + error.headers.message);
    };
    client.connect(username, password, connect_callback, error_callback);
}

function myDisconnect() {
    client.disconnect(function () {
        alert("已断开连接");
    });
}
function mySubscribe() {
    subsId = client.subscribe(destination, subscribe_callback);
}
function myUnsubscribe() {
    client.unsubscribe(subsId);
}