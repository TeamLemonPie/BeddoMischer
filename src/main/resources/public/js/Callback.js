function onLoad() {
    var connection = new WebSocket('ws://localhost:9999/callback');

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
    };
}