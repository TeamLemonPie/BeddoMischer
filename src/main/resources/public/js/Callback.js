function onLoad() {
    var connection = new WebSocket('ws://' + location.hostname + ':' + location.port + '/callback');

    // When the connection is open, send some data to the server
    connection.onopen = function () {
        connection.send('Ping'); // Send the message 'Ping' to the server
    };

    // Log errors
    connection.onerror = function (error) {
        console.log('WebSocket Error ' + error);
    };

    connection.onclose = function (code, reason) {
        console.log("connection closed")
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