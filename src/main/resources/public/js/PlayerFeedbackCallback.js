/*
Handles all player callback events from server.
 */
function handlePlayerFeedbackCallback(command, key, value) {
    let playerContainer = $("#player-" + key);
    if (command === "seat") {
        document.location.reload();
    }
    else if (command === "name") {
        let playerName = $(playerContainer).find(".player-name");
        playerName.text(value)
    } else if (command === "card") {
        console.log($(playerContainer).find(".card"));
        console.log(value);
        if (value === 2) {
            $(playerContainer).find(".card").removeClass("red");
            $(playerContainer).find(".card").removeClass("orange");
            $(playerContainer).find(".card").addClass("green");
        } else if (value === 1) {
            $(playerContainer).find(".card").removeClass("green");
            $(playerContainer).find(".card").removeClass("red");
            $(playerContainer).find(".card").addClass("orange");
        } else if (value === 0) {
            $(playerContainer).find(".card").removeClass("green");
            $(playerContainer).find(".card").removeClass("orange");
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

function handlePlayerCallback(command, key, value) {
    let playerContainer = $("#player-" + key);
    if (command === "state") {
        if (value === "OUT_OF_ROUND") {
            playerContainer.find(".card").addClass("out-of-round");
            playerContainer.removeClass("out-of-game");
            playerContainer.removeClass("active");
        } else if (value === "OUT_OF_GAME") {
            playerContainer.find(".card").removeClass("out-of-round");
            playerContainer.addClass("out-of-game");
            playerContainer.removeClass("active");
        } else if (value === "ACTIVE") {
            playerContainer.find(".card").removeClass("out-of-round");
            playerContainer.removeClass("out-of-game");
            playerContainer.addClass("active");
        }
    }
}

function handleBoardCallback(command, key, value) {
    if (command === "small-blind") {
        $("#small-blind").text(value.toLocaleString());
    } else if (command === "big-blind") {
        $("#big-blind").text(value.toLocaleString());
    } else if (command === "ante") {
        $("#ante").text(value.toLocaleString());
    }
}