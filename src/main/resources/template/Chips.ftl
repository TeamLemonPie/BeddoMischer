<html>
<head>
    <link rel="stylesheet" href="css/results.css">

    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/Callback.js"></script>
    <script src="js/ChipsCallback.js"></script>
</head>
<body onload="onLoad()">
<div class="main-container">
    <div class="headline">
        Zwischenergebnisse
    </div>
    <div class="table">
        <div class="table-content">
        <#list players as player>
            <#import "ChipItem.ftl" as m>
            <@m.chipContainer player player?index + 1/>
        </#list>
        </div>
    </div>
</div>
</body>
</html>