<html>
<head>
    <link rel="stylesheet" href="css/main.css">

    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/Callback.js"></script>
    <script src="js/ChipsCallback.js"></script>
</head>
<body onload="onLoad()">
<div class="table-container">
<#list players as player>
    <#import "ChipItem.ftl" as m>
    <@m.chipContainer player/>
</#list>
</div>
</body>
</html>