<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <link href="https://cdn.bootcss.com/limonte-sweetalert2/7.21.1/sweetalert2.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/limonte-sweetalert2/7.21.1/sweetalert2.min.js"></script>
    <title>受访结果</title>
</head>
<style type="text/css">
    body{
        overflow-x:hidden;
        position: absolute;
        top: 0px;
        left: 0px;
        bottom: 0px;
        right: 0px;
        margin: 0px;
        background: white;
    }
    .owner_bind {
        position: absolute;
        height: 100%;
        width: 100%;
    }
    .visitorSuc {
        width: 60%;
        margin: 20px auto;
    }
    .visitorSuc img {
        width: 100%;
        margin-top: 15%;
    }
    .success {
        font-size: 18px;
        text-align: center;
        line-height: 28px;
    }
    .message {
        width: 80%;
        margin: 0 auto;
    }
    .tipMessage {
        font-size: 16px;
        text-align: center;
        line-height: 28px;
        color: rgb(165, 165, 165);
    }
    .tipMessage2 {
        margin-top: 20px;
        font-size: 14px;
        text-align: center;
        line-height: 28px;
        color: #fc4b6b;
    }
    p {
        margin: 0px;
    }
    .visitorInvalid {
        width: 60%;
        margin: 20px auto;
        position: relative;
    }
    .visitorInvalid img.code {
        width: 100%;
        margin-top: 15%;
        opacity: 0.3;
    }
    .disableImg {
        position: absolute;
        width: 35%;
        bottom: 6%;
        right: -5%;
    }
</style>
<script type="text/javascript">
    var websocket = null;

    //连接发生错误的回调方法
    websocket.onerror = function(){
        alert("error")
    };

    //连接成功建立的回调方法
    websocket.onopen = function(event){
        alert("open");
    }

    //接收到消息的回调方法
    websocket.onmessage  = function(event){
        onMessage(event);
    }
    function onMessage(event){
        console.log(event);
        $("#buildName").attr("value", event.data);
    }

    //连接关闭的回调方法
    websocket.onclose = function(){
        alert("关闭回调");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function(){
        websocket.close();
    }

    //发送消息
    function send(){
        var message = document.getElementById('visitingName').value;
        websocket.send(message);
    }

    function submit() {
        //判断当前浏览器是否支持WebSocket
        if('WebSocket' in window){
            websocket = new WebSocket("ws://localhost:8762/websocket");
        }
        else{
            alert('Not support websocket')
        }
    }
    //关闭连接
    function closeWebSocket(){
        websocket.close();
        alert("关闭")
    }
</script>
<body>
<div class="owner_bind">
    <button class="bindBtn" onclick="submit()">链接</button>
    <button class="bindBtn" onclick="closeWebSocket()">关闭</button>
    <div class="cell_content">
        <p class="input_content">
            <span class="title">您的姓名</span><span class="star">*</span>
        </p>
        <input type="text" class="cell_input" id="visitingName" />
    </div>
    <button class="bindBtn" onclick="send()">发送</button>
    <input type="text" class="cell_input" id="buildName"/>
</div>
</body>
</html>