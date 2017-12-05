/*
Handles all player callback events from server.
 */
function handlePlayerFeedbackCallback(command, key, value) {
    var playerContainer = $("#player-" + key);
    if (command === "name") {
        var playerName = $(playerContainer).find(".player-name");
        playerName.text(value)
    } else if (command === "card") {
        if (value) {
            $(playerContainer).find(".card").removeClass("red");
        } else {
            $(playerContainer).find(".card").addClass("red");
        }

    } else if (command === "next-pause") {
        clearInterval(x);
        startCountdown(value, true);
        $("#countdown-description").text("nächste Pause:");
    } else if (command === "pause") {
        clearInterval(x);
        startCountdown(value, true);
        $("#countdown-description").text("Pause:");
    }
}