<html>
<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="css/playerFeedback.css">
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/Callback.js"></script>
    <script src="js/Countdown.js"></script>
    <script src="js/PlayerFeedbackCallback.js"></script>
</head>
<body onload="onLoad()">
<#-- @ftlvariable name="players" type="java.util.List<de.lemonpie.beddomischer.model.Player>" -->
<table id="table-main" cellspacing="0" cellpadding="0">
    <tr>
        <td colspan="2">
            <div class="player">
            <@player 6/>
            </div>
        </td>
        <td colspan="9" class="countdown-text" id="countdown-description">nächste Pause:</td>
        <td colspan="2">
            <div class="player">
            <@player 0/>
            </div>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <div class="player">
            <@player 5/>
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
            <@player 1/>
            </div>
        </td>
    </tr>
    <tr>
        <td class="spacer"></td>
        <td>
            <div class="player">
            <@player 4/>
            </div>
        </td>
        <td class="spacer"></td>
        <td colspan="7">
            <div class="player">
            <@player 3/>
            </div>
        </td>
        <td class="spacer"></td>
        <td>
            <div class="player">
            <@player 2/>
            </div>
        </td>
        <td class="spacer"></td>
    </tr>
</table>
</body>
</html>

<#macro player id>
<table class="table-player" id="player-<#if id < players?size>${players[id].id}<#else>-${id}</#if>">
    <tr>
        <td class="player-name"><#if id < players?size>${players[id].name}</#if></td>
    </tr>
    <tr class="player-spacer-line"></tr>
    <tr>
        <td>
            <div class="card <#if id < players?size && players[id].card1 == "back" && players[id].card2 == "back">red<#else></#if>"></div>
        </td>
    </tr>
</table>
</#macro>