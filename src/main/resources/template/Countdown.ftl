<html>
<head>
    <link rel="stylesheet" href="css/results.css">

    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/Countdown.js"></script>
    <script src="js/Callback.js"></script>
    <script src="js/CountdownCallback.js"></script>
</head>
<body onload="onLoad()">
<div class="main-container">
    <table class="countdown" cellspacing="0" cellpadding="0">
        <tr>
            <td class="countdown-time countdown-spacer" id="countdown" data="${time?c}">00:00</td>
            <td class="countdown-time" id="countdown-endtime">${endTime}</td>
        </tr>
        <tr>
            <td class="countdown-text countdown-spacer">MINUTEN</td>
            <td class="countdown-text">WEITER UM</td>
        </tr>
        <tr>
            <td class="countdown-bottom countdown-spacer">&nbsp;</td>
            <td class="countdown-bottom"></td>
        </tr>
    </table>
</div>
</body>
</html>