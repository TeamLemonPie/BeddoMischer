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
    } else if (command === "hide") {
        if (value === true) {
            playerContainer.addClass("hide");
        } else {
            playerContainer.removeClass("hide");
        }
    } else if (command === "card") {
        var cardId = value.index;
        var playerCard1 = $(playerContainer).find("#card" + cardId);

        playerCard1.removeAttr("class");
        playerCard1.addClass("card");
        playerCard1.addClass(value.card);
    } else if (command === "player-op") {
        if (value === "add") {
            loadPlayer(key);
        } else if (value === "remove") {
            removePlayer(key);
        }
    }
}

function loadPlayer(id) {
    $.get("player/" + id, function (data) {
        $(".left-container").append(data);

        var $wrapper = $('.left-container');
        $wrapper.find('.player-container').sort(function (a, b) {
            return +a.dataset.id - +b.dataset.id;
        })
            .appendTo($wrapper);

    });
}

function removePlayer(id) {
    $("#player-" + id).remove();
}