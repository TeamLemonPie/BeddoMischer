/*
Handles all board callback events from server.
 */

function handleBoardCallback(command, key, value) {
    if (command === "card") {
        let boardCards = $(".card" + key);
        let ghostCards = $(".card" + key + "_ghost");

        console.log(command + " " + key + " " + value);

        ghostCards.each(function () {
            let ghostCard = $(this);
            console.log(ghostCard);
            let originalClassListGhost = ghostCard.attr("class").split(' ');
            let originalClassGhost = originalClassListGhost[originalClassListGhost.length - 1];
            console.log(originalClassGhost);

            ghostCard.removeAttr("class");
            ghostCard.addClass("card");
            ghostCard.addClass(value);
            ghostCard.addClass(originalClassGhost);
            ghostCard.fadeOut(0);

            ghostCard.fadeIn(1000, function () {
                boardCards.each(function () {
                    let boardCard = $(this);
                    let originalClassListBoard = ghostCard.attr("class").split(' ');
                    let originalClassBoard = originalClassListBoard[originalClassListBoard.length - 1];
                    console.log(originalClassBoard);


                    boardCard.removeAttr("class");
                    boardCard.addClass("card");
                    boardCard.addClass(value);
                    boardCard.addClass(originalClassBoard);
                    boardCard.fadeIn(0);
                    ghostCard.removeClass("card");
                });
            });
        });
    } else if (command === "small-blind") {
        $("#small-blind").text(value);
    } else if (command === "big-blind") {
        $("#big-blind").text(value);
    } else if (command === "ante") {
        $("#ante").text(value);
    }
}