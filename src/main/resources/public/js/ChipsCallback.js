/*
Handles all chip callback events from server.
 */

function handleChipCallback(command, key, value) {
    if (command === "chip") {
        window.location = window.location;
    }
}

function handlePlayerCallback(command, key, value) {
    let playerContainer = $("#player-" + key);
    if (command === "name") {
        let playerName = $(playerContainer).find("#player-name");
        playerName.text(value)
    } else if (command === "twitchName") {
        let playerTwitchName = $(playerContainer).find("#player-twitchName");
        playerTwitchName.text(value)
    } else if (command === "player-op") {
        if (value === "add") {
            loadPlayer(key);
        } else if (value === "remove") {
            removePlayer(key);
        }
    }
}

function loadPlayer(id) {
    $.get("chips/" + id, function (data) {
        $(".table-container").append(data);
    });
}

function removePlayer(id) {
    $("#player-" + id).remove();
}