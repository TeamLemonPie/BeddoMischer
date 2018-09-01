function fullAnimation(animation) {
    setTimeout(function () {
        hideBoard();
    }, 900);

    setTimeout(function () {
        animation.play();
    }, 2500);

    setTimeout(function () {
        showCardsForLowerThird();
    }, 4850);

    setTimeout(function () {
        hideCardsForLowerThird();
    }, 10000);

    setTimeout(function () {
        showBoard();
    }, 11700);
}

function hideBoard() {
    $(".cards-board").each(function (i, obj) {
        hideCard(obj, i, 80);
    });

    setTimeout(function () {
        $("#board-animation-container").animate({width: 'toggle'}, 1400);
    }, 1000);
}

function showBoard() {
    $("#board-animation-container").animate({width: 'toggle'}, 1400);

    setTimeout(function () {
        $(".cards-board").each(function (i, obj) {
            hideCard(obj, i, 120);
        });
    }, 940);
}

function hideCard(object, index, delayPerIterationInMs) {
    setTimeout(function () {
        object.classList.toggle('move-card-down');
    }, index * delayPerIterationInMs);
}

function showCardsForLowerThird() {
    $(".cards-lower-third").each(function (i, obj) {
        setTimeout(function () {
            obj.classList.toggle('move-card-up');
        }, i * 120);
    });
}

function hideCardsForLowerThird() {
    $($(".cards-lower-third").get().reverse()).each(function (i, obj) {
        obj.classList.add("move-card-up-slow");
        setTimeout(function () {
            obj.classList.toggle('move-card-up');
        }, i * 120);
    });
}