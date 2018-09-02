<html>
<head>
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/cards.css">

    <script src="js/libs/jquery-3.2.1.min.js"></script>
    <script src="js/Callback.js"></script>
    <script src="js/PlayerCallback.js"></script>

</head>
<body onload="onLoad()">
<script type="text/javascript">
    let serverHide = false;
    serverHide = ${hide?c};
    if (serverHide !== null) {
        let localStorage = window.localStorage;
        localStorage.setItem(HIDE_PLAYER, serverHide);
    }
</script>
<div id="left-container">

    <#list players as player>
        <#import "PlayerItem.ftl" as m>
        <@m.playerContainer player/>
    </#list>
</div>
</body>
</html>