/*
Handles all board callback events from server.
 */

function handleBoardCallback(command, key, value) {
    if (command === "card") {
        let playerCard = $("#card" + key);
        let ghostCard = $("#card" + key + "_ghost");

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

    } else if (command === "small-blind") {
        $("#small-blind").text(value);
    } else if (command === "big-blind") {
        $("#big-blind").text(value);
    } else if (command === "ante") {
        $("#ante").text(value);
    }
}