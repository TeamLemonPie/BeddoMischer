<html>
<head>
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/cards.css">

    <script src="js/Callback.js"></script>
    <script src="js/BoardCallback.js"></script>
    <script src="js/jquery-3.2.1.min.js"></script>
</head>
<body onload="onLoad()">
<div class="main-board-container">
    <div class="board-container center-text">
        <div class="board center-text">
        <#list board as card>
            <div class="card ${card}"></div>
        </#list>
        </div>
        <div class="banner-board">
            <div class="banner-table">
                <div class="player-info">
                    <div class="player-name center-text">
                        Tisch
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>