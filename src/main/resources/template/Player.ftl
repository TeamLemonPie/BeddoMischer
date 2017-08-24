<html>
<head>
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/cards.css">

    <script src="js/Callback.js"></script>
    <script src="js/PlayerCallback.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body onload="onLoad()">
<div class="left-container">

<#list players as player>
    <#import "PlayerItem.ftl" as m>
    <@m.playerContainer player/>
</#list>
</div>
</body>
</html>