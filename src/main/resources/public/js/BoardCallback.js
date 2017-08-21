/*
Handles all board callback events from server.
 */

function handleBoardCallback(command, key, value) {
    if (command === "card") {
        var cardElement = $($(".card").get(key));
        cardElement.removeAttr("class");
        cardElement.addClass("card");
        cardElement.addClass(value);
    }
}