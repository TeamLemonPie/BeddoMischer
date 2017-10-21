/*
Handles all board callback events from server.
 */

function handleBoardCallback(command, key, value) {
    if (command === "card") {
        var playerCard = $("#card" + key);
        var ghostCard = $("#card" + key + "_ghost");

        ghostCard.removeAttr("class");
        ghostCard.addClass("card");
        ghostCard.addClass(value);
        ghostCard.fadeOut(0);

        ghostCard.fadeIn(1000, function () {
            playerCard.removeAttr("class");
            playerCard.addClass("card");
            playerCard.addClass(value);
            playerCard.fadeIn(0);
            ghostCard.removeAttr("class");
        });

    }
}