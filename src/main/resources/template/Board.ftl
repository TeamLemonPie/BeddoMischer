<html>
    <head>
        <link rel="stylesheet" href="css/board.css">
        <link rel="stylesheet" href="css/cards.css">

        <script src="js/libs/jquery-3.2.1.min.js"></script>
    <#--<script src="js/Callback.js"></script>-->
    <#--<script src="js/BoardCallback.js"></script>-->
        <script src="js/test.js"></script>
    </head>
    <!--<body onload="onLoad()">-->
    <body>
        <div class="main-board-container">
            <div id="layer-video" class="layer">
                <video width="900" height="220" autoplay>
                    <source src="https://tobisan.thecodelabs.de/AE/Board.mp4" type="video/mp4">
                </video>
            </div>
            <div id="layer-cards" class="layer">
                <div class="cards">
                    <#list board as card>
                        <div class="card-stack">
                            <div class="card ${card}" id="card${card?index}"></div>
                            <div class="card" id="card${card?index}_ghost"></div>
                        </div></#list>
                </div>
            </div>
            <div id="layer-board" class="layer">
                <div class="banner-board">
                    <div class="banner-table">
                        <table id="table-board" cellpadding="0" cellspacing="0" border="0">
                            <tr>
                                <td class="blind">Small: <span id="small-blind">500</span></td>
                                <td class="blind">Big: <span id="big-blind">1020</span></td>
                                <td class="blind">Ante: <span id="ante">12345</span></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>