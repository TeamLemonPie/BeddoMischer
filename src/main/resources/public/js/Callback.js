let connection;

function onLoad() {
    let url = 'ws://' + location.hostname + ':' + location.port + '/callback';
    connection = new WebSocket(url);

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

    // Handle messages
    connection.onmessage = function (e) {
        console.log('Server: ' + e.data);

        let data = JSON.parse(e.data);
        if (data.scope === "PLAYER") {
            try {
                handlePlayerCallback(data.command, data.key, data.value);
            } catch (e) {
            } finally {
            }

        } else if (data.scope === "BOARD") {
            try {
                handleBoardCallback(data.command, data.key, data.value);
            } catch (e) {
            } finally {
            }
        } else if (data.scope === "CHIP") {
            try {
                handleChipCallback(data.command, data.key, data.value);
            } catch (e) {
            } finally {
            }
        } else if (data.scope === "COUNTDOWN") {
            try {
                handleCountdownCallback(data.command, data.key, data.value);
            } catch (e) {
            } finally {
            }
        } else if (data.scope === "PLAYER_FEEDBACK") {

            try {
                handlePlayerFeedbackCallback(data.command, data.key, data.value);
            } catch (e) {
            } finally {
            }
        } else if (data.scope === "DIRECTOR") {
            try {
                handleDirectorCallback(data.command, data.key, data.value);
            } catch (e) {
            } finally {
            }
        }
    };
}