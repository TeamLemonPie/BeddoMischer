/*
Handles all director callback events from server.
 */

function handleDirectorCallback(command, key, value) {
    if (command === "lower_third_start") {
        let animation = bodymovin.loadAnimation({
            container: document.getElementById("animation"),
            renderer: "svg",
            path: "/lowerthird/" + key,
            autoplay: false
        });

        if (isBoardVisible()) {
            animation.addEventListener('complete', function () {
                animation.destroy();
            });

            fullAnimation(animation, key);
        } else {
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
}