/*
Handles all player callback events from server.
 */

function handlePlayerCallback(command, key, value) {
    var playerContainer = $("#player-" + key);
    if (command === "name") {
        var playerName = $(playerContainer).find("#player-name");
        playerName.text(value)
    } else if (command === "twitchName") {
        var playerTwitchName = $(playerContainer).find("#player-twitchName");
        playerTwitchName.text(value)
    } else if (command === "card0") {
        var playerCard1 = $(playerContainer).find("#card1");
        playerCard1.removeAttr("class");
        playerCard1.addClass(value);
        playerCard1.addClass("card");
    } else if (command === "card1") {
        var playerCard2 = $(playerContainer).find("#card2");
        playerCard2.removeAttr("class");
        playerCard2.addClass(value);
        playerCard2.addClass("card");
    } else if (command === "player-op") {
        if (value === "add") {
            loadPlayer(key);
        }
    }
}

function loadPlayer(id) {
    $.get("player/" + id, function (data) {
        $(".left-container").append(data);
    });
}