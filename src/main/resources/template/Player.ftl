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
    <div class="player-container">

        <div class="deck">
            <div class="card ${player.card1}" id="card1"></div>
            <div class="card ${player.card2}" id="card2"></div>
        </div>
        <div class="banner">
            <div class="player-info">
                <div class="player-name" id="player-name">
                ${player.name}
                </div>
                <div class="player-name player-subname" id="player-twitchName">
                ${player.twitchName}
                </div>
            </div>
        </div>
    </div>
</#list>
</div>
</body>
</html>