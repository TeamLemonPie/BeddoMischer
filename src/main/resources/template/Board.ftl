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
            <div class="card-stack">
                <div class="card ${card}" id="card${card?index}"></div>
                <div class="card" id="card${card?index}_ghost"></div>
            </div>&nbsp;
        </#list>
        </div>
        <div class="banner-board">
            <div class="banner-table">
                <div class="player-info">
                    <table id="table-board" cellpadding="0" cellspacing="0">
                        <tr>
                            <td rowspan="2" id="table-text">TISCH</td>
                            <td class="blind">SMALL BLIND:</td>
                            <td class="blind blind-number" id="small-blind"></td>
                        </tr>
                        <tr>
                            <td class="blind">BIG BLIND:</td>
                            <td class="blind blind-number" id="big-blind"></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>