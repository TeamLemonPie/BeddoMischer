<html>
<head>
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/cards.css">

    <script src="js/libs/jquery-3.2.1.min.js"></script>
    <script src="js/Callback.js"></script>
    <script src="js/PlayerCallback.js"></script>
</head>
<body onload="onLoad()">
<div id="left-container">

<#list players as player>
    <#import "PlayerItem.ftl" as m>
    <@m.playerContainer player/>
</#list>
</div>
</body>
</html>