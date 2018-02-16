$(document).ready(function () {
    startCountdown($("#countdown").attr("data"));
});

var x;

function startCountdown(time, warn = false) {
    if(parseInt(time) === 0)
    {
        setCountdownDigits("0000");
        setEndtimeDigits("0000");
        return;
    }

    var countDownDate = new Date(parseInt(time));
    try {
        setEndtimeDigits(pad(countDownDate.getHours(), 2) + pad(countDownDate.getMinutes(), 2));
    } catch (e) {
    } finally {
    }

    var $countdown = $(".countdown");
    $countdown.removeClass("warning-none");
    $countdown.removeClass("warning-red");
    $countdown.removeClass("warning-orange");

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

        if(minutes >= 100)
        {
            setCountdownDigits("9999");
            return;
        }

        setCountdownDigits(pad(minutes, 2) + pad(seconds, 2));

        if (warn && minutes === 0) {
            if (seconds <= 30) {
                $countdown.addClass("warning-red");
                $countdown.removeClass("warning-orange");
                $countdown.removeClass("warning-none");
            } else if (seconds <= 60) {
                $countdown.addClass("warning-orange");
                $countdown.removeClass("warning-red");
                $countdown.removeClass("warning-none");
            } else {
                $countdown.addClass("warning-none");
                $countdown.removeClass("warning-red");
                $countdown.removeClass("warning-orange");
            }
        }

        if (distance <= 0) {
            clearInterval(x);
            setCountdownDigits("0000");
        }
    }, 1000);
}

function setCountdownDigits(countdownString) {
    document.getElementById("countdown-minute-1").innerHTML = countdownString[0];
    document.getElementById("countdown-minute-2").innerHTML = countdownString[1];
    document.getElementById("countdown-second-1").innerHTML = countdownString[2];
    document.getElementById("countdown-second-2").innerHTML = countdownString[3];
}

function setEndtimeDigits(countdownString) {
    if (document.getElementById("countdown-endtime-hour-1") != null) {
        document.getElementById("countdown-endtime-hour-1").innerHTML = countdownString[0];
        document.getElementById("countdown-endtime-hour-2").innerHTML = countdownString[1];
        document.getElementById("countdown-endtime-minute-1").innerHTML = countdownString[2];
        document.getElementById("countdown-endtime-minute-2").innerHTML = countdownString[3];
    }
}

function pad(num, size) {
    var s = num + "";
    while (s.length < size) s = "0" + s;
    return s;
}