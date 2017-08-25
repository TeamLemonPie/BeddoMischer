function onLoad() {
    var url = 'ws://' + location.hostname + ':' + location.port + '/callback';
    var connection = new WebSocket(url);

    // When the connection is open, send some data to the server
    connection.onopen = function () {
        console.log("Connected to: " + url)
    };

    // Log errors
    connection.onerror = function (error) {
        console.log('WebSocket Error ' + error);
    };

    connection.onclose = function (code, reason) {
        setTimeout(function () {
            onLoad()
        }, 5000);
    };

    // Log messages from the server
    connection.onmessage = function (e) {
        console.log('Server: ' + e.data);

        var data = JSON.parse(e.data);
        if (data.scope === "player") {
            handlePlayerCallback(data.command, data.key, data.value);
        } else if (data.scope === "board") {
            handleBoardCallback(data.command, data.key, data.value);
        }
    };
}