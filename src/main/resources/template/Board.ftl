<html>
    <head>
        <link rel="stylesheet" href="css/board.css">
        <link rel="stylesheet" href="css/cards.css">

        <script src="js/libs/jquery-3.2.1.min.js"></script>
        <script src="js/Callback.js"></script>
        <script src="js/BoardCallback.js"></script>
        <script src="js/BoardAnimation.js"></script>
    </head>
    <body onload="onLoad()">
    <body>
        <div class="main-board-container">
            <div id="layer-video" class="layer">
                <video width="900" height="220" autoplay>
                    <source src="https://tobisan.thecodelabs.de/AE/Board.mp4" type="video/mp4">
                </video>
            </div>
            <div id="layer-cards" class="layer">
                <div class="cards-container">
                    <#list board as card>
                        <div class="card-stack cards-board">
                            <div class="card ${card} card${card?index}"></div>
                            <div class="card card${card?index}_ghost"></div>
                        </div></#list>
                </div>
            </div>
            <div id="layer-board" class="layer">
                <div id="board-animation-container">
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
            <div id="layer-lower-third" class="layer">
                <div class="cards-container-lower-thirds">
                    <#list board as card>
                        <div class="card-stack cards-lower-third">
                            <div class="card ${card} card${card?index}"></div>
                            <div class="card card${card?index}_ghost"></div>
                        </div></#list>
                </div>
            </div>
        </div>
    </body>
</html>