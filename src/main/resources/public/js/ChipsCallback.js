/*
Handles all chip callback events from server.
 */

function handleChipCallback(command, key, value) {
    if (command === "chip") {
        var playerContainer = $("#player-" + key);
        var playerName = $(playerContainer).find("#player-chip");
        playerName.text(value)
    }
}