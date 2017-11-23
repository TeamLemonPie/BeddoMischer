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

function handlePlayerFeedbackCallback(command, key, value) {
    var playerContainer = $("#player-" + key);
    if (command === "name") {
        var playerName = $(playerContainer).find("#player-name");
        playerName.text(value)
    } else if (command === "card") {
        var cardId = value.index;

        var playerCard = $(playerContainer).find("#card" + cardId);
        var ghostCard = $(playerContainer).find("#card" + cardId + "_ghost");

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

    } else if (command === "next-pause") {
        clearInterval(x);
        startCountdown(value);
    }
}