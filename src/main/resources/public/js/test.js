$(document).ready(function () {
    setTimeout(function () {
        hideBoard();
    }, 1000);
    setTimeout(function () {
        showBoard();
    }, 12000);
});

function hideBoard() {
    $(".card-stack").each(function (i, obj) {
        hideCardsFromLeft();
    });

    setTimeout(function () {
        $("#layer-board").animate({width: 'toggle'}, 1300);
    }, 1000);
}


function hideCardsFromLeft() {
    $(".card-stack").each(function (i, obj) {
        setTimeout(function () {
            obj.classList.toggle('move-card-down');
        }, i * 160);
    });
}

function hideCardsFromRight() {
    $($(".card-stack").get().reverse()).each(function (i, obj) {
        setTimeout(function () {
            obj.classList.toggle('move-card-down');
        }, i * 160);
    });
}

function showBoard() {
    $("#layer-board").animate({width: 'toggle'}, 1300);

    setTimeout(function () {
        $(".card-stack").each(function (i, obj) {
            setTimeout(function () {
                obj.classList.toggle('move-card-down');
            }, i * 160);
        });
    }, 1000);
}