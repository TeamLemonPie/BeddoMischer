$(document).ready(function () {
    startCountdown($("#countdown").attr("data"));
});

var x;

function startCountdown(time) {
    var countDownDate = new Date(parseInt(time));
    try {
        document.getElementById("countdown-endtime").innerHTML = pad(countDownDate.getHours(), 2) + ":" + pad(countDownDate.getMinutes(), 2);
    } catch (e) {
    } finally {
    }

    // Update the count down every 1 second
    x = setInterval(function () {

        // Get todays date and time
        var now = new Date().getTime();

        // Find the distance between now an the count down date
        var distance = countDownDate.getTime() - now;

        // Time calculations for days, hours, minutes and seconds
        var hours = Math.floor((distance % (1000 * 60 * 60 * 60)) / (1000 * 60 * 60));
        var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60)) + hours * 60;
        var seconds = Math.floor((distance % (1000 * 60)) / 1000);

        // Display the result in the element with id="demo"
        document.getElementById("countdown").innerHTML = pad(minutes, 2) + ":" + pad(seconds, 2);

        // If the count down is finished, write some text
        if (distance <= 0) {
            clearInterval(x);
            document.getElementById("countdown").innerHTML = "00:00";
        }
    }, 1000);
}

function pad(num, size) {
    var s = num + "";
    while (s.length < size) s = "0" + s;
    return s;
}