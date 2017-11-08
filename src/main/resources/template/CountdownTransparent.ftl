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
    <table class="countdown-transparent" cellspacing="0" cellpadding="0">
        <tr>
            <td class="countdown-transparent-time" id="countdown" data="${time?c}">00:00</td>
        </tr>
        <tr>
            <td class="countdown-transparent-text">MINUTEN</td>
        </tr>
        <tr>
            <td>
                <br>
            </td>
        </tr>
        <tr>
            <td class="countdown-transparent-time" id="countdown-endtime">${endTime}</td>
        </tr>
        <tr>
            <td class="countdown-transparent-text">WEITER UM</td>
        </tr>
    </table>
</div>
</body>
</html>