function handleCountdownCallback(command, key, value) {
    if (command === "pause") {
        clearInterval(x);
        startCountdown(value);
    }
}