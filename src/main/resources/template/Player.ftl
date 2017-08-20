<html>
<head>
    <link rel="stylesheet" href="css/main.css">
</head>
<body onload="onLoad()">
<div class="left-container">
<#list players as player>
    <div class="player-container">

        <div class="deck">
            <div class="card" style="background-image: url('img/${player.card1}.png');"></div>
            <div class="card" style="background-image: url('img/${player.card2}.png');"></div>
        </div>
        <div class="banner">
            <div class="player-info">
                <div class="player-name">
                ${player.name}
                </div>
                <div class="player-name player-subname">
                ${player.twitchName}
                </div>
            </div>
        </div>
    </div>
</#list>
</div>
</body>
</html>