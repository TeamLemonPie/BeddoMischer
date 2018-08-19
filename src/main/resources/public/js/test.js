$(document).ready(function () {
    // $("#layer-board").animate({width:'toggle'}, 1000);

    $(".card-stack").each(function (i, obj) {
        setTimeout(function () {
            // $(this).animate({bottom: '-71'}, {duration: 1000, queue: false});
            obj.classList.toggle('x');
        }, i * 200);
    });
});