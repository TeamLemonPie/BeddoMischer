/*
Handles all player callback events from server.
 */

$(document).ready(function () {
    $(".player-container").each(function () {
        if ($(this).attr("data-hide") !== "ACTIVE") {
            $(this).animate({height: 'hide', opacity: 'hide'}, 0);
        }
    })
});

function handlePlayerCallback(command, key, value) {
    let playerContainer = $("#player-" + key);
    if (command === "name") {
        let playerName = $(playerContainer).find("#player-name");
        playerName.text(value)
    } else if (command === "twitchName") {
        let playerTwitchName = $(playerContainer).find("#player-twitchName");
        playerTwitchName.text(value)
    } else if (command === "state") {
        if (value !== "ACTIVE") {
            playerContainer.animate({height: 'hide', opacity: 'hide'}, 1000);
        } else {
            playerContainer.animate({height: 'show', opacity: 'show'}, 1000);
        }
    } else if (command === "card") {
        let cardId = value.index;

        let playerCard = $(playerContainer).find("#card" + cardId);
        let ghostCard = $(playerContainer).find("#card" + cardId + "_ghost");

        ghostCard.removeAttr("class");
        ghostCard.addClass("card");
        ghostCard.addClass(value.card);
        ghostCard.fadeOut(0);

        ghostCard.fadeIn(1000, function () {
            playerCard.removeAttr("class");
            playerCard.addClass("card");
            playerCard.addClass(value.card);
            playerCard.fadeIn(0);
            ghostCard.removeAttr("class");
        });

    } else if (command === "player-op") {
        if (value === "add") {
            loadPlayer(key);
        } else if (value === "remove") {
            removePlayer(key);
        }
    } else if (command === "winProbability") {
        let winProbability = $(playerContainer).find(".percentage-banner");
        winProbability.text(value + "%")
    }
}

function loadPlayer(id) {
    $.get("player/" + id, function (data) {
        let container = $(".left-container");
        container.append(data);

        container.find('.player-container').sort(function (a, b) {
            return +a.dataset.id - +b.dataset.id;
        }).appendTo(container);

        let player = $("#player-" + id);
        player.stop(true).fadeIn({
            duration: 1000,
            queue: false
        }).css('display', 'none').slideDown(1000);
    });
}

function removePlayer(id) {
    let player = $("#player-" + id);
    player.animate({height: 'toggle', opacity: 'toggle'}, 1000, function () {
        player.remove();
    });
}