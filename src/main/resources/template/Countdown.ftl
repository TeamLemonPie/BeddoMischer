<html>
<head>
    <link rel="stylesheet" href="css/results.css">

    <script src="js/libs/jquery-3.2.1.min.js"></script>
    <script src="js/Countdown.js"></script>
    <script src="js/Callback.js"></script>
    <script src="js/CountdownCallback.js"></script>
</head>
<body onload="onLoad()">
<div class="main-container">
    <table class="countdown" cellspacing="0" cellpadding="0">
        <tr>
            <td class="hide" id="countdown" data="${time?c}"></td>
            <td class="countdown-time" id="countdown-minute-1">0</td>
            <td class="countdown-time" id="countdown-minute-2">0</td>
            <td class="countdown-time countdown-transparent-colon">:</td>
            <td class="countdown-time" id="countdown-second-1">0</td>
            <td class="countdown-time countdown-spacer" id="countdown-second-2">0</td>

            <td class="hide" id="countdown-endtime" data="${time?c}"></td>
            <td class="countdown-time countdown-padding-left" id="countdown-endtime-hour-1">0</td>
            <td class="countdown-time" id="countdown-endtime-hour-2">0</td>
            <td class="countdown-time countdown-transparent-colon">:</td>
            <td class="countdown-time" id="countdown-endtime-minute-1">0</td>
            <td class="countdown-time" id="countdown-endtime-minute-2">0</td>
        </tr>
        <tr>
            <td colspan="5" class="countdown-text countdown-spacer">MINUTEN</td>
            <td colspan="5" class="countdown-text countdown-padding-left">WEITER UM</td>
        </tr>
        <tr>
            <td colspan="5" class="countdown-bottom countdown-spacer">&nbsp;</td>
            <td colspan="5" id="scroll-anchor" class="countdown-bottom"></td>
        </tr>
    </table>
</div>
</body>
</html>