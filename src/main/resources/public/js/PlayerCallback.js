let HIDE_PLAYER = "hidePlayer";

$(document).ready(function () {
    let localStorage = window.localStorage;
    let isHidden = localStorage.getItem(HIDE_PLAYER);
    if (isHidden === null) {
        isHidden = "false";
        localStorage.setItem(HIDE_PLAYER, isHidden);
    }

    if (isHidden === "true") {
        $(".hide-board").each(function (e) {
            $(this).css("opacity", "0");
        });
        $("#left-container").fadeOut(0);
    }
});


$(document).ready(function () {
    $(".player-container").each(function () {
        if ($(this).attr("data-hide") !== "ACTIVE") {
            $(this).animate({height: 'hide', opacity: 'hide'}, 0);
        }
    })
});

/*
Handles all player callback events from server.
 */

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
        let winProbability = $(playerContainer).find(".percentage");
        winProbability.text(value)
    } else if (command === "highlight") {
        let activeIcon = $(playerContainer).find(".active-icon");
        if (value) {
            activeIcon.fadeIn(500);
        }
        else {
            activeIcon.fadeOut(500);
        }
    } else if (command === "overlay_hide") {
        $(".hide-board").each(function (e) {
            $(this).css("opacity", "1");
        });

        if (value) {
            $("#left-container").fadeOut(500);
        } else {
            $("#left-container").fadeIn(500);
        }
        localStorage.setItem(HIDE_PLAYER, value);
    }
}

function loadPlayer(id) {
    $.get("player/" + id, function (data) {
        let container = $("#left-container");
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