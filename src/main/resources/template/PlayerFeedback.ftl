<html>
    <head>
        <meta charset="UTF-8"/>
        <link rel="stylesheet" href="css/playerFeedback.css">
        <script src="js/libs/jquery-3.2.1.min.js"></script>
        <script src="js/Callback.js"></script>
        <script src="js/Countdown.js"></script>
        <script src="js/PlayerFeedbackCallback.js"></script>
        <meta charset="UTF-8">
    </head>
    <body onload="onLoad()">
    <#-- @ftlvariable name="players" type="java.util.List<de.lemonpie.beddomischer.model.player.Player>" -->
        <table id="table-main" cellspacing="0" cellpadding="0">
            <tr>
                <td colspan="2">
                    <div class="player">
                        <@player seats[6]/>
                    </div>
                </td>
                <td colspan="9" class="countdown-text" id="countdown-description">nächste Pause:</td>
                <td colspan="2">
                    <div class="player">
                        <@player seats[0]/>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div class="player">
                        <@player seats[5]/>
                    </div>
                </td>
                <td class="hide" id="countdown" data="${time?c}"></td>
                <td></td>
                <td class="countdown round-left"></td>
                <td class="countdown" id="countdown-minute-1">0</td>
                <td class="countdown" id="countdown-minute-2">0</td>
                <td class="countdown countdown-transparent-colon">:</td>
                <td class="countdown" id="countdown-second-1">0</td>
                <td class="countdown" id="countdown-second-2">0</td>
                <td class="countdown round-right"></td>
                <td></td>
                <td colspan="2">
                    <div class="player">
                        <@player seats[1]/>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="spacer"></td>
                <td>
                    <div class="player">
                        <@player seats[4]/>
                    </div>
                </td>
                <td class="spacer"></td>
                <td colspan="7">
                    <div class="player">
                        <@player seats[3]/>
                    </div>
                </td>
                <td class="spacer"></td>
                <td>
                    <div class="player">
                        <@player seats[2]/>
                    </div>
                </td>
                <td class="spacer"></td>
            </tr>
            <tr>
                <td class="spacer"></td>
                <td>
                    <div class="player-name">
                        Small Blind:
                        <div class="blind" id="small-blind">
                        ${small_blind}
                        </div>
                    </div>
                </td>
                <td></td>
                <td colspan="7">
                    <div class="player-name">
                        Big Blind:
                        <div class="blind" id="big-blind">
                        ${big_blind}
                        </div>
                    </div>
                </td>
                <td></td>
                <td>
                    <div class="player-name">
                        Ante:
                        <div class="blind" id="ante">
                        ${ante}
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </body>
</html>

<#macro player playerId>
    <#if playerId gte 0>
        <table class="table-player" id="player-${players[playerId].id}">
            <tr>
                <td class="player-name">${players[playerId].name}</td>
            </tr>
            <tr class="player-spacer-line"></tr>
            <tr>
                <td>
                    <#if players[playerId].card1 == "back" && players[playerId].card2 == "back">
                        <div class="card red"></div>
                    <#elseif players[playerId].card1 != "back"  && players[playerId].card2 != "back">
                        <div class="card green"></div>
                    <#else>
                       <div class="card orange"></div>
                    </#if>
                </td>
            </tr>
        </table>
    <#else>
        <table class="table-player" id="player-${playerId}">
            <tr>
                <td class="player-name"></td>
            </tr>
            <tr class="player-spacer-line"></tr>
            <tr>
                <td>
                    <div class="card"></div>
                </td>
            </tr>
        </table>
    </#if>
</#macro>