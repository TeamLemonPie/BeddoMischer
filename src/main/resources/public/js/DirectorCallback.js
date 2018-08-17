/*
Handles all director callback events from server.
 */

function handleDirectorCallback(command, key, value) {
    if (command === "lower_third_start") {
        let animation = bodymovin.loadAnimation({
            container: document.getElementById("lower-third"),
            renderer: "svg",
            path: "/lowerthird/" + key
        });

        animation.addEventListener('complete', function () {
            animation.destroy();

            connection.send(JSON.stringify({
                scope: "DIRECTOR",
                command: "lower_third_finish",
                key: key
            }));
        });

        animation.play();
    }
}